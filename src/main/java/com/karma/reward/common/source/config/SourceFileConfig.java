package com.karma.reward.common.source.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.karma.reward.common.codec.config.CodecConfig;
import com.karma.reward.common.config.resource.FileConfig;

import java.util.List;

public class SourceFileConfig extends FileConfig implements SourceConfig {

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public SourceFileConfig(
			@JsonProperty(value = "type", required = false) String type,
			@JsonProperty(value = "codec", required = true) CodecConfig codecConfig,
			@JsonProperty(value = "paths", required = true) List<String> paths
	) {
		super(type, codecConfig, paths);
	}
}
