package com.karma.reward.core;

import com.karma.reward.core.config.AppConfig;
import com.karma.reward.core.source.Source;
import com.karma.reward.core.source.SourceProvider;
import com.karma.reward.core.spark.SparkUtil;
import org.apache.spark.sql.SparkSession;

import java.io.Serializable;

public abstract class App<T extends AppConfig> implements Serializable {

	protected final SparkSession sparkSession;
	protected final Source source;

	public App(final T config) {
		sparkSession =
				SparkUtil.getSparkSession(
						config.getJobName(),
						config.getSparkConfig()
				);
		source = SourceProvider.getSource(config.getSourceConfig());
	}

	public void run() throws Exception {
		execute();
		shutdown();
	}

	public abstract void execute() throws Exception;

	public void shutdown() throws Exception {
		//System.in.read(); // to keep running the execution
		sparkSession.stop();
		source.shutdown();
	}

	;
}
