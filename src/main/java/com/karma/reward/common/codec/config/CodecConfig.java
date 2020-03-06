package com.karma.reward.common.codec.config;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type"
)
@JsonSubTypes({
		@JsonSubTypes.Type(name = "csv", value = CSVCodecConfig.class),
		@JsonSubTypes.Type(name = "json", value = JSONCodecConfig.class)
})
public abstract class CodecConfig {

	private final String type;

	public CodecConfig(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
