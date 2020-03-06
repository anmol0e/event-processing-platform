package com.karma.reward.common.codec;

import java.io.Serializable;

public class CodecProvider implements Serializable {

	private static final CSVCodec csvCodec = new CSVCodec();
	private static final JSONCodec jsonCodec = new JSONCodec();

	private CodecProvider() {
	}

	public static Codec getCodec(CodecType codecType) {
		if (codecType == CodecType.CSV) {
			return csvCodec;
		} else if (codecType == CodecType.JSON) {
			return jsonCodec;
		} else {
			throw new RuntimeException("Unsupported codec");
		}
	}

	public enum CodecType {
		CSV("csv"), JSON("json");

		private String codecType;

		CodecType(String codecType) {
			this.codecType = codecType;
		}

		public static CodecType getCodecType(String codecType) {
			if (codecType.equalsIgnoreCase("csv")) {
				return CSV;
			} else if (codecType.equalsIgnoreCase("json")) {
				return JSON;
			} else {
				return null;
			}
		}
	}
}
