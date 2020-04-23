package com.karma.reward.core.codec.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CSVCodecConfig implements CodecConfig {

	private final String[][] schema;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public CSVCodecConfig(
			@JsonProperty(value = "schema", required = true) String[][] schema
	) {
		this.schema = schema;
	}

	@Override
	public String[][] getSchema() {
		return schema;
	}
}
