package com.karma.reward.common;

import org.apache.spark.SparkConf;

import java.util.Map;

public class SparkUtil {

	private SparkUtil() {
	}

	public static SparkConf getSparkConf(final String appName, Map<String, String> configs) {
		SparkConf sparkConf = new SparkConf();
		sparkConf.setAppName(appName);
		configs.forEach(sparkConf::set);
		return sparkConf;
	}
}
