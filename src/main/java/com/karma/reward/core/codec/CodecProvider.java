package com.karma.reward.core.codec;

import com.karma.reward.core.codec.config.CSVCodecConfig;
import com.karma.reward.core.codec.config.CodecConfig;
import com.karma.reward.core.codec.config.JSONCodecConfig;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CodecProvider implements Serializable {

	private static final Map<Class<? extends Codec>, Codec> CACHE = new HashMap<>();

	private CodecProvider() {
	}

	public static Codec getCodec(CodecConfig codecConfig) {
		Class<? extends Codec> clazz;
		if (codecConfig instanceof CSVCodecConfig) {
			clazz = CSVCodec.class;
		} else if (codecConfig instanceof JSONCodecConfig) {
			clazz = JSONCodec.class;
		} else {
			throw new RuntimeException("Unsupported codec");
		}
		synchronized (CACHE) {
			Codec codec = CACHE.getOrDefault(clazz, null);
			if (codec == null) {
				if (codecConfig instanceof CSVCodecConfig) {
					codec = new CSVCodec((CSVCodecConfig) codecConfig);
				} else if (codecConfig instanceof JSONCodecConfig) {
					codec = new JSONCodec((JSONCodecConfig) codecConfig);
				}
				CACHE.put(clazz, codec);
			}
			return codec;
		}
	}
}
