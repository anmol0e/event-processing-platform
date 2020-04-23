package com.karma.reward.core.source.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.karma.reward.core.codec.config.CodecConfig;
import com.karma.reward.core.config.resource.FileConfig;

public class SourceFileConfig extends FileConfig implements SourceConfig {

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public SourceFileConfig(
			@JsonProperty(value = "codec", required = true) CodecConfig codecConfig,
			@JsonProperty(value = "paths", required = true) String[] paths
	) {
		super(codecConfig, paths);
	}
}
