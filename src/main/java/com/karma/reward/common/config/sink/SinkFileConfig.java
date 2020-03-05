package com.karma.reward.common.config.sink;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.karma.reward.common.config.resource.FileConfig;

import java.util.List;


public class SinkFileConfig extends FileConfig implements Sink {

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public SinkFileConfig(
			@JsonProperty(value = "format", required = true) String format,
			@JsonProperty(value = "paths", required = true) List<String> paths
	) {
		super(format, paths);
	}
}
