package dev.firebird.dimensionz.forge;

import dev.firebird.dimensionz.DimensionZ;
import dev.firebird.dimensionz.forge.client.DimensionZClientForge;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DimensionZ.MOD_ID)
public class DimensionZForge {
    public DimensionZForge(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
		new DimensionZ();

		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> DimensionZClientForge::new);
    }
}
