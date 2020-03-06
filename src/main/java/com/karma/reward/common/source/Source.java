package com.karma.reward.common.source;

import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;

import java.util.Optional;
import java.util.function.Function;


public interface Source {

	<T> Function<SparkContext, Optional<RDD<T>>> read(Class<T> clazz) throws Exception;

	default void shutdown() throws Exception {

	}
}
