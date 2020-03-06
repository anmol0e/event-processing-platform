package com.karma.reward.common.codec;

import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLContext;

public interface Codec {

	void code();

	<T> RDD<T> decode(SQLContext sqlc, Dataset<String> dataSet, Class<T> clazz, String... schema);
}
