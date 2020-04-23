package com.karma.reward.core.codec.config;

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
public interface CodecConfig {

	String[][] getSchema();
}
