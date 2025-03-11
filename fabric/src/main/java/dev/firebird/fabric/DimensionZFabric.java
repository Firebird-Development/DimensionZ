package dev.firebird.fabric;

import dev.firebird.dimensionz.DimensionZ;
import net.fabricmc.api.ModInitializer;

public class DimensionZFabric implements ModInitializer {
	@Override
	public void onInitialize() {
		new DimensionZ();
	}
}