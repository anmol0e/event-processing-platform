package com.karma.reward.common.codec;

import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLContext;

public class JSONCodec implements Codec {

	@Override
	public void code() {
	}

	@Override
	public <T> RDD<T> decode(SQLContext sc, Dataset<String> dataSet, Class<T> clazz, String... schema) {
		return null;
	}
}
