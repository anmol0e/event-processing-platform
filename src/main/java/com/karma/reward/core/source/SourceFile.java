package com.karma.reward.core.source;

import com.karma.reward.core.codec.CodecProvider;
import com.karma.reward.core.source.config.SourceFileConfig;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.function.Function;

public class SourceFile implements Source {

	private final SourceFileConfig config;

	public SourceFile(SourceFileConfig config) {
		this.config = config;
	}

	@Override
	public <T> Function<SparkSession, Dataset<T>> read(MapFunction<Row, T> mapper, Class<T> clazz) {
		return sparkSession ->
				CodecProvider.getCodec(config.getCodecConfig())
						.decode(
								sparkSession,
								sparkSession.read().textFile(config.getPaths()),    //todo we can optimize by selecting only required columns
								mapper,
								clazz
						);
	}
}
