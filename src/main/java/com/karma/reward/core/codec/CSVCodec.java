package com.karma.reward.core.codec;

import com.karma.reward.core.codec.config.CSVCodecConfig;
import com.karma.reward.core.spark.SparkUtil;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;

public class CSVCodec implements Codec {

	private StructType schema;

	public CSVCodec(CSVCodecConfig config) {
		this.schema = SparkUtil.getStructType(config.getSchema());
	}

	@Override
	public void code() {

	}

	@Override
	public <T> Dataset<T> decode(SparkSession sparkSession, Dataset<String> dataSet, MapFunction<Row, T> mapper, Class<T> clazz) {
		return sparkSession
				.read().schema(schema)
				.option("header", true)
				.option("inferSchema", false)
				.csv(dataSet)
				.map(mapper, Encoders.bean(clazz));
	}
}
