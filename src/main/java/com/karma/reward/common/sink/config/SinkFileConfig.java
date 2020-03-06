package com.karma.reward.common.sink.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.karma.reward.common.codec.config.CodecConfig;
import com.karma.reward.common.config.resource.FileConfig;

import java.util.List;

public class SinkFileConfig extends FileConfig implements SinkConfig {

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public SinkFileConfig(
			@JsonProperty(value = "type", required = false) String type,
			@JsonProperty(value = "codec", required = true) CodecConfig codecConfig,
			@JsonProperty(value = "paths", required = true) List<String> paths
	) {
		super(type, codecConfig, paths);
	}
}
