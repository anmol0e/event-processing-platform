package com.karma.reward.rewardpoint;

import com.karma.reward.common.App;
import com.karma.reward.common.ObjectMapperProvider;
import org.apache.spark.rdd.RDD;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class RewardPointApp extends App<RewardPointConfig> {

	private static Logger logger = Logger.getLogger(RewardPointApp.class.getCanonicalName());

	private final RewardPointConfig config;

	private RewardPointApp(RewardPointConfig config) {
		super(config);
		this.config = config;
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			logger.log(Level.SEVERE, "Application requires configuration file");
		}
		RewardPointConfig config =
				ObjectMapperProvider.getObjectMapper().readValue(
						new FileInputStream(new File(args[0])),
						RewardPointConfig.class
				);
		new RewardPointApp(config).execute();
	}

	@Override
	public void execute() {
		Optional<RDD<String>> rddOptional = config.getSource().getResource().apply(sc);
		if (!rddOptional.isPresent()) {
			throw new RuntimeException("Empty RDD");
		}
	}
}
