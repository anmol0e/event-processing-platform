package com.karma.reward.common.source;

import com.karma.reward.common.codec.Codec;
import com.karma.reward.common.codec.CodecProvider;
import com.karma.reward.common.source.config.SourceFileConfig;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SQLContext;

import java.util.Optional;
import java.util.function.Function;

public class SourceFile implements Source {

	private final SourceFileConfig config;

	public SourceFile(SourceFileConfig config) {
		this.config = config;
	}

	@Override
	public <T> Function<SparkContext, Optional<RDD<T>>> read(Class<T> clazz) {
		return sc -> {
			// collate all files as RDD<String>
			Optional<RDD<String>> rddOptional = config.getPaths()
					.stream()
					.map(
							path -> sc.textFile(
									path,
									sc.defaultMinPartitions()
							)
					)
					.reduce(RDD::$plus$plus);
			if (!rddOptional.isPresent()) {
				throw new RuntimeException("Empty RDD");
			}
			// decode based on source type
			Codec codec = CodecProvider.getCodec(CodecProvider.CodecType.getCodecType(config.getCodecConfig().getType()));
			SQLContext sqlc = new SQLContext(sc);
			return Optional.of(
					codec.decode(
							sqlc,
							sqlc.createDataset(rddOptional.get(), Encoders.bean(String.class)),
							clazz,
							config.getCodecConfig().
					)
			);
		};
	}
}
