package com.karma.reward.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


public class ObjectMapperProvider {

	private static final ObjectMapper yamlObjectMapper = customizeObjectMapper(new ObjectMapper(new YAMLFactory()));
	private static final ObjectMapper csvObjectMapper = customizeObjectMapper(new ObjectMapper(new CsvFactory()));
	private static final ObjectMapper jsonObjectMapper = customizeObjectMapper(new ObjectMapper());

	public static ObjectMapper getYAMLObjectMapper() {
		return yamlObjectMapper;
	}

	public static ObjectMapper getCSVObjectMapper() {
		return csvObjectMapper;
	}

	public static ObjectMapper getJSONObjectMapper() {
		return jsonObjectMapper;
	}

	private static ObjectMapper customizeObjectMapper(ObjectMapper objectMapper) {
		objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
		objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}
}

