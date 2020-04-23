package com.karma.reward.rewardpoint;

import com.karma.reward.core.App;
import com.karma.reward.core.ObjectMapperProvider;
import com.karma.reward.core.drools.DroolsUtil;
import com.karma.reward.core.sink.Sink;
import com.karma.reward.core.sink.SinkProvider;
import com.karma.reward.rewardpoint.dataobject.RewardPoints;
import com.karma.reward.rewardpoint.dataobject.Transaction;
import com.karma.reward.rewardpoint.spark.RowMapper;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.kie.api.runtime.StatelessKieSession;
import scala.Function1;
import scala.Tuple2;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RewardPointApp extends App<RewardPointConfig> {

	private static Logger logger = Logger.getLogger(RewardPointApp.class.getCanonicalName());

	private final String rules;
	private final Sink sink;

	private RewardPointApp(RewardPointConfig config) {
		super(config);
		rules = config.getRules();
		sink = SinkProvider.getSink(config.getSinkConfig());
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			logger.log(Level.SEVERE, "Application requires configuration file");
		}
		RewardPointConfig config =
				ObjectMapperProvider.getYAMLObjectMapper().readValue(
						new FileInputStream(new File(args[0])),
						RewardPointConfig.class
				);
		new RewardPointApp(config).run();
	}

	@Override
	public void execute() throws Exception {
		Dataset<Transaction> ds = source.read(new RowMapper(), Transaction.class).apply(sparkSession);
		Dataset<RewardPoints> rpds = getRewardPoints(ds);
		sink.write().accept(rpds);
	}

	@Override
	public void shutdown() throws Exception {
		super.shutdown();
		sink.shutdown();
	}

	Dataset<RewardPoints> getRewardPoints(Dataset<Transaction> ds) {
		String rulesFile = rules;   // Assigining to avoid serialization this class
		// Calculate reward points as per the rules
		ds = ds.mapPartitions(
				(Function1<scala.collection.Iterator<Transaction>, scala.collection.Iterator<Transaction>> & Serializable) transactions -> {
					StatelessKieSession session = DroolsUtil.getStatelessSession(rulesFile);
					return transactions.map(transaction -> {
						session.execute(transaction);
						return transaction;
					});
				},
				Encoders.bean(Transaction.class)
		);
		return getCollatedRewardPointsPerCustomer(ds);
	}

	Dataset<RewardPoints> getCollatedRewardPointsPerCustomer(Dataset<Transaction> ds) {
		JavaPairRDD<String, Double> rewardPointsPerCustomer =
				ds.javaRDD().mapToPair(
						transaction -> new Tuple2<>(
								transaction.getCustomerId(),
								transaction.getRewardPoints()
						)
				);
		// Collate all reward points from every customer across all transactions
		// and min cap reward points to 0
		rewardPointsPerCustomer = rewardPointsPerCustomer
				.aggregateByKey(0.0, Double::sum, Double::sum)
				.mapValues(rewardPoints -> Math.max(0.0, rewardPoints));
		List<Tuple2<String, Double>> a = rewardPointsPerCustomer.collect();
		System.out.println(a);
		// join
		JavaPairRDD<String, String> customerName = ds.javaRDD()
				.mapToPair(
						transaction -> new Tuple2<>(
								transaction.getCustomerId(),
								transaction.getCustomerName()
						)
				)
				.distinct();
		RDD<RewardPoints> rdd = rewardPointsPerCustomer.join(customerName)
				.map(
						row -> {
							RewardPoints rp = new RewardPoints();
							rp.setCustomerId(row._1);
							rp.setCustomerName(row._2._2);
							rp.setRewardPoints(Double.valueOf(Math.floor(row._2._1)).intValue());
							return rp;
						}
				)
				.rdd();
		return sparkSession.createDataset(rdd, Encoders.bean(RewardPoints.class));
	}
}
