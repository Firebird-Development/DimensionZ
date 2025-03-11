package dev.firebird.dimensionz.fabric.client;

import dev.firebird.dimensionz.client.DimensionZClient;
import net.fabricmc.api.ClientModInitializer;

public class DimensionZClientFabric implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		new DimensionZClient();
	}
}
