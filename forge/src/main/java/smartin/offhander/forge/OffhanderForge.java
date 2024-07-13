package smartin.offhander.forge;

import dev.architectury.platform.Platform;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.api.distmarker.Dist;
import smartin.offhander.Offhander;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Offhander.MOD_ID)
public class OffhanderForge {
    public OffhanderForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Offhander.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        Offhander.init();
        if (Platform.getEnv() == Dist.CLIENT) {
            OffhanderForgeClient.setup();
        }
    }
}