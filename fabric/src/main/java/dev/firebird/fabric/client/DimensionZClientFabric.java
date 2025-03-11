package dev.firebird.fabric.client;

import dev.firebird.dimensionz.neoforge.client.DimensionZClient;
import net.fabricmc.api.ClientModInitializer;

public class DimensionZClientFabric implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		new DimensionZClient();
	}
}
