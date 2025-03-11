package dev.firebird.dimensionz.client;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class DimensionZClient {
	private static final Logger LOGGER = LogUtils.getLogger();

	private static DimensionZClient INSTANCE;

	public DimensionZClient() {
		LOGGER.info("Hello client world!");

		INSTANCE = this;
	}

	public static DimensionZClient getInstance() {
		return INSTANCE;
	}
}
