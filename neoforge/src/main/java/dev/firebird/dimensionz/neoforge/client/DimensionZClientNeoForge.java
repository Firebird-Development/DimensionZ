package dev.firebird.dimensionz.neoforge.client;

import dev.firebird.dimensionz.DimensionZ;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = DimensionZ.MOD_ID, dist = Dist.CLIENT)
public class DimensionZClientNeoForge {
	public DimensionZClientNeoForge(IEventBus modEventBus, ModContainer modContainer) {
		new DimensionZClient();
	}
}
