package com.karma.reward.common.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.karma.reward.common.config.source.Source;

import java.util.Map;

public abstract class AppConfig {

	private final String jobName;
	private final Map<String, String> sparkConfig;
	private final Source source;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public AppConfig(
			@JsonProperty(value = "jobName", required = true) String jobName,
			@JsonProperty(value = "sparkConfig", required = false) Map<String, String> sparkConfig,
			@JsonProperty(value = "source", required = true) Source source
	) {
		this.jobName = jobName;
		this.sparkConfig = sparkConfig;
		this.source = source;
	}

	public String getJobName() {
		return jobName;
	}

	public Map<String, String> getSparkConfig() {
		return sparkConfig;
	}

	public Source getSource() {
		return source;
	}
}
