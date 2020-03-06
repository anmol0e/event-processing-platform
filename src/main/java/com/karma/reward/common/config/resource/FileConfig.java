package com.karma.reward.common.config.resource;

import com.karma.reward.common.codec.config.CodecConfig;

import java.util.List;

public abstract class FileConfig {

	protected final String type;
	protected final CodecConfig codecConfig;
	protected final List<String> paths;

	public FileConfig(String type, CodecConfig codecConfig, List<String> paths) {
		this.type = type;
		this.codecConfig = codecConfig;
		this.paths = paths;
	}

	public CodecConfig getCodecConfig() {
		return codecConfig;
	}

	public List<String> getPaths() {
		return paths;
	}
}
