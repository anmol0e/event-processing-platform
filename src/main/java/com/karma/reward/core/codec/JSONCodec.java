package com.karma.reward.core.codec;

import com.karma.reward.core.codec.config.JSONCodecConfig;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class JSONCodec implements Codec {

	public JSONCodec(JSONCodecConfig codecConfig) {
	}

	@Override
	public void code() {
	}

	@Override
	public <T> Dataset<T> decode(SparkSession sparkSession, Dataset<String> dataSet, MapFunction<Row, T> mapper, Class<T> clazz) {
		throw new RuntimeException("Unsupported");
	}
}
