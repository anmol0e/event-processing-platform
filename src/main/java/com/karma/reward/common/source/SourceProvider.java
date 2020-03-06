package com.karma.reward.common.source;

import com.karma.reward.common.source.config.SourceConfig;
import com.karma.reward.common.source.config.SourceFileConfig;

public class SourceProvider {

	private SourceProvider() {
	}

	public static Source getSource(SourceConfig sourceConfig) {
		if (sourceConfig instanceof SourceFileConfig) {
			SourceFileConfig sourceFileConfig = (SourceFileConfig) sourceConfig;
			return new SourceFile(sourceFileConfig);
		} else {
			throw new RuntimeException("Unsupported source type");
		}
	}
}
