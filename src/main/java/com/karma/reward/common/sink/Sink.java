package com.karma.reward.common.sink;

import org.apache.spark.rdd.RDD;

import java.util.function.Consumer;


public interface Sink {

	Consumer<RDD<?>> write();

	default void shutdown() throws Exception {
	}
}
