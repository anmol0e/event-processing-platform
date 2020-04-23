package com.karma.reward.core.spark;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.*;

import java.util.Map;
import java.util.stream.Stream;

public class SparkUtil {

	private SparkUtil() {
	}

	public static SparkSession getSparkSession(final String appName, Map<String, String> configs) {
		SparkSession.Builder builder = SparkSession.builder();
		builder.appName(appName);
		configs.forEach(builder::config);
		return builder.getOrCreate();
	}

	public static StructType getStructType(String[][] schema) {
		return new StructType(
				Stream.of(schema).map(
						pair -> new StructField(
								pair[0],
								getDataTypeFromString(pair[1]),
								false,
								Metadata.empty()
						)
				).toArray(StructField[]::new)
		);
	}

	private static DataType getDataTypeFromString(String representation) {
		switch (representation) {
			case "String":
				return StringType$.MODULE$;
			case "Double":
				return DoubleType$.MODULE$;
			case "Integer":
				return IntegerType$.MODULE$;
			default:
				throw new RuntimeException("Not mapped yet");
		}
	}
}
