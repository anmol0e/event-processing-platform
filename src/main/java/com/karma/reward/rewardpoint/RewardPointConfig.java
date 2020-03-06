package com.karma.reward.rewardpoint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.karma.reward.common.config.AppConfig;
import com.karma.reward.common.sink.config.SinkConfig;
import com.karma.reward.common.source.config.SourceConfig;

import java.util.Map;

public class RewardPointConfig extends AppConfig {

	private final SinkConfig sinkConfig;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public RewardPointConfig(
			@JsonProperty(value = "jobName", required = true) String jobName,
			@JsonProperty(value = "spark", required = false) Map<String, String> sparkConfig,
			@JsonProperty(value = "source", required = true) SourceConfig sourceConfig,
			@JsonProperty(value = "sink", required = true) SinkConfig sinkConfig
	) {
		super(jobName, sparkConfig, sourceConfig);
		this.sinkConfig = sinkConfig;
	}

	public SinkConfig getSinkConfig() {
		return sinkConfig;
	}
}
