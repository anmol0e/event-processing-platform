package com.karma.reward.core.config.resource;

import com.karma.reward.core.codec.config.CodecConfig;

public abstract class FileConfig {

	protected final CodecConfig codecConfig;
	protected final String[] paths;

	public FileConfig(CodecConfig codecConfig, String[] paths) {
		this.codecConfig = codecConfig;
		this.paths = paths;
	}

	public CodecConfig getCodecConfig() {
		return codecConfig;
	}

	public String[] getPaths() {
		return paths;
	}
}
