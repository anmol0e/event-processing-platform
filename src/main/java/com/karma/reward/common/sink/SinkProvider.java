package com.karma.reward.common.sink;

import com.karma.reward.common.sink.config.SinkConfig;
import com.karma.reward.common.sink.config.SinkFileConfig;

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
