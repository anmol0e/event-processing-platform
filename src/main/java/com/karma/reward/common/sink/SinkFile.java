package com.karma.reward.common.sink;

import com.karma.reward.common.sink.config.SinkFileConfig;
import org.apache.spark.rdd.RDD;

import java.util.function.Consumer;

public class SinkFile implements Sink {

	private final SinkFileConfig config;

	public SinkFile(SinkFileConfig config) {
		this.config = config;
	}

	@Override
	public Consumer<RDD<?>> write() {
		return rdd -> rdd.saveAsTextFile(config.getPaths().get(0));
	}
}
