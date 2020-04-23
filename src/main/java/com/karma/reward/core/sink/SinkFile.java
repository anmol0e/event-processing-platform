package com.karma.reward.core.sink;

import com.karma.reward.core.sink.config.SinkFileConfig;
import org.apache.spark.sql.Dataset;

import java.util.function.Consumer;

public class SinkFile implements Sink {

	private final SinkFileConfig config;

	public SinkFile(SinkFileConfig config) {
		this.config = config;
	}

	@Override
	public Consumer<Dataset<?>> write() {
		return ds -> ds.write().option("header", true).csv(config.getPaths()[0]);
	}
}
