package com.karma.reward.core.sink;

import org.apache.spark.sql.Dataset;

import java.util.function.Consumer;


public interface Sink {

	Consumer<Dataset<?>> write();

	default void shutdown() throws Exception {
	}
}
