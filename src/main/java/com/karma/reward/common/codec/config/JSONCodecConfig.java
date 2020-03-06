package com.karma.reward.common.codec.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JSONCodecConfig extends CodecConfig {

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public JSONCodecConfig(
			@JsonProperty(value = "type", required = false) String type
	) {
		super(type);
	}
}
