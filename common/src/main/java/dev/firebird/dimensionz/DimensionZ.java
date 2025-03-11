package dev.firebird.dimensionz;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class DimensionZ {
	public static final String MOD_ID = "dimensionz";
	private static final Logger LOGGER = LogUtils.getLogger();

	private static DimensionZ INSTANCE;

	public DimensionZ() {
		LOGGER.info("Hello world!");

		INSTANCE = this;
	}

	public static DimensionZ getInstance() {
		return INSTANCE;
	}
}