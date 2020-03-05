package com.karma.reward.common.config.source;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.karma.reward.common.config.resource.FileConfig;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class SourceFileConfig extends FileConfig implements Source {

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public SourceFileConfig(
			@JsonProperty(value = "format", required = true) String format,
			@JsonProperty(value = "paths", required = true) List<String> paths
	) {
		super(format, paths);
	}

	@Override
	public Function<SparkContext, Optional<RDD<String>>> getResource() {
		return sc -> paths
				.stream()
				.map(
						path -> sc.textFile(
								path,
								sc.defaultMinPartitions()
						)
				)
				.reduce(RDD::$plus$plus);
	}
}
