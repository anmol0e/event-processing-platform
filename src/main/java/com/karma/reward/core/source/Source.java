package com.karma.reward.core.source;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.function.Function;


public interface Source {

	<T> Function<SparkSession, Dataset<T>> read(MapFunction<Row, T> mapper, Class<T> clazz) throws Exception;

	default void shutdown() throws Exception {

	}
}
