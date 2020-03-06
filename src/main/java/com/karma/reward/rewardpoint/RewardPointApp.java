package com.karma.reward.rewardpoint;

import com.karma.reward.common.App;
import com.karma.reward.common.ObjectMapperProvider;
import com.karma.reward.common.sink.Sink;
import com.karma.reward.common.sink.SinkProvider;
import com.karma.reward.rewardpoint.dataobject.Transaction;
import org.apache.spark.rdd.RDD;

import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RewardPointApp extends App<RewardPointConfig> {

	private static Logger logger = Logger.getLogger(RewardPointApp.class.getCanonicalName());

	private final Sink sink;

	private RewardPointApp(RewardPointConfig config) throws Exception {
		super(config);
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
	public void shutdown() throws Exception {
		super.shutdown();
		sink.shutdown();
	}

	@Override
	public void execute() throws Exception {
		Optional<RDD<Transaction>> rddOptional = source.read(Transaction.class).apply(sc);
		if (!rddOptional.isPresent()) {
			throw new RuntimeException("Empty RDD");
		}
		sink.write().accept(rddOptional.get());
	}
}
