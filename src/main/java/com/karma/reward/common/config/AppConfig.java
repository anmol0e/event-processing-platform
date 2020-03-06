package com.karma.reward.common.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.karma.reward.common.source.config.SourceConfig;

import java.util.Map;

public abstract class AppConfig {

	private final String jobName;
	private final Map<String, String> sparkConfig;
	private final SourceConfig sourceConfig;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public AppConfig(
			@JsonProperty(value = "jobName", required = true) String jobName,
			@JsonProperty(value = "spark", required = false) Map<String, String> sparkConfig,
			@JsonProperty(value = "source", required = true) SourceConfig sourceConfig
	) {
		this.jobName = jobName;
		this.sparkConfig = sparkConfig;
		this.sourceConfig = sourceConfig;
	}

	public String getJobName() {
		return jobName;
	}

	public Map<String, String> getSparkConfig() {
		return sparkConfig;
	}

	public SourceConfig getSourceConfig() {
		return sourceConfig;
	}
}
