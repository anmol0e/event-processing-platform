package com.karma.reward.common.config.source;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;

import java.util.Optional;
import java.util.function.Function;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type"
)
@JsonSubTypes({
		@JsonSubTypes.Type(name = "file", value = SourceFileConfig.class)
})
public interface Source {

	Function<SparkContext, Optional<RDD<String>>> getResource();
}
