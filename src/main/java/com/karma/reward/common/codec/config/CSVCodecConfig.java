package com.karma.reward.common.codec.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CSVCodecConfig extends CodecConfig {

	private final List<List<String>> schema;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public CSVCodecConfig(
			@JsonProperty(value = "type", required = false) String type,
			@JsonProperty(value = "schema", required = true) List<List<String>> schema
	) {
		super(type);
		this.schema = schema;
	}
}
