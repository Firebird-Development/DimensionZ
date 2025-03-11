package dev.firebird.dimensionz.neoforge;

import dev.firebird.dimensionz.DimensionZ;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(DimensionZ.MOD_ID)
public class DimensionZNeoForge {
	public DimensionZNeoForge(IEventBus modEventBus, ModContainer modContainer) {
		new DimensionZ();
	}
}
