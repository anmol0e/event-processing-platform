package com.karma.reward.core.codec.config;

import com.fasterxml.jackson.annotation.JsonCreator;

public class JSONCodecConfig implements CodecConfig {

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public JSONCodecConfig() {
	}

	@Override
	public String[][] getSchema() {
		return new String[0][0];
	}
}
