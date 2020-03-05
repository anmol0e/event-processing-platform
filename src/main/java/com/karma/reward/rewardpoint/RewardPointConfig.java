package com.karma.reward.rewardpoint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.karma.reward.common.config.AppConfig;
import com.karma.reward.common.config.sink.Sink;
import com.karma.reward.common.config.source.Source;

import java.util.Map;

public class RewardPointConfig extends AppConfig {

	private final Sink sink;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public RewardPointConfig(
			@JsonProperty(value = "jobName", required = true) String jobName,
			@JsonProperty(value = "sparkConfig", required = false) Map<String, String> sparkConfig,
			@JsonProperty(value = "source", required = true) Source source,
			@JsonProperty(value = "sink", required = true) Sink sink
	) {
		super(jobName, sparkConfig, source);
		this.sink = sink;
	}
}
