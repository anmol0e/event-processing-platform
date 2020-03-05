package com.karma.reward.common.config.resource;

import java.util.List;

public abstract class FileConfig {

	protected final String format;
	protected final List<String> paths;

	public FileConfig(String format, List<String> paths) {
		this.format = format;
		this.paths = paths;
	}

	public String getFormat() {
		return format;
	}

	public List<String> getPaths() {
		return paths;
	}
}
