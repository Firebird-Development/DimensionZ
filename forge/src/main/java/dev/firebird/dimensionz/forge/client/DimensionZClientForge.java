package dev.firebird.dimensionz.forge.client;

import dev.firebird.dimensionz.neoforge.client.DimensionZClient;

// Dont mark this with @OnlyIn(Dist.Client)
public class DimensionZClientForge {
	public DimensionZClientForge() {
		new DimensionZClient();
	}
}
