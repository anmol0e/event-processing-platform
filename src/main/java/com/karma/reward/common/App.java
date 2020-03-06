package com.karma.reward.common;

import com.karma.reward.common.config.AppConfig;
import com.karma.reward.common.source.Source;
import com.karma.reward.common.source.SourceProvider;
import org.apache.spark.SparkContext;

public abstract class App<T extends AppConfig> {

	protected final SparkContext sc;
	protected final Source source;

	public App(final T config) throws Exception {
		sc = new SparkContext(
				SparkUtil.getSparkConf(
						config.getJobName(),
						config.getSparkConfig()
				)
		);
		source = SourceProvider.getSource(config.getSourceConfig());
	}

	public void run() throws Exception {
		execute();
		shutdown();
	}

	public abstract void execute() throws Exception;

	public void shutdown() throws Exception {
		System.in.read();
		sc.stop();
		source.shutdown();
	}

	;
}
