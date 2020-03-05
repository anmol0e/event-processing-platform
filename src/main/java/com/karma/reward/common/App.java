package com.karma.reward.common;

import com.karma.reward.common.config.AppConfig;
import org.apache.spark.SparkContext;

public abstract class App<T extends AppConfig> {

	protected final SparkContext sc;

	public App(T config) {
		sc = new SparkContext(
				SparkUtil.getSparkConf(
						config.getJobName(),
						config.getSparkConfig()
				)
		);
	}

	public abstract void execute();
}
