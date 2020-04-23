package com.karma.reward.core.config;

import com.karma.reward.core.source.config.SourceConfig;

import java.util.Map;

public abstract class AppConfig {

	private final String jobName;
	private final Map<String, String> sparkConfig;
	private final SourceConfig sourceConfig;

	public AppConfig(
			String jobName,
			Map<String, String> sparkConfig,
			SourceConfig sourceConfig
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
