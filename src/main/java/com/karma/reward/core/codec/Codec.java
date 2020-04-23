package com.karma.reward.core.codec;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public interface Codec {

	void code();

	<T> Dataset<T> decode(SparkSession sparkSession, Dataset<String> dataSet, MapFunction<Row, T> mapper, Class<T> clazz);
}
