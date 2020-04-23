package com.karma.reward.core.sink;

import com.karma.reward.core.sink.config.SinkConfig;
import com.karma.reward.core.sink.config.SinkFileConfig;

public class SinkProvider {

	private SinkProvider() {
	}

	public static Sink getSink(SinkConfig sinkConfig) {
		if (sinkConfig instanceof SinkFileConfig) {
			SinkFileConfig sinkFileConfig = (SinkFileConfig) sinkConfig;
			return new SinkFile(sinkFileConfig);
		} else {
			throw new RuntimeException("Unsupported sink type");
		}
	}
}
