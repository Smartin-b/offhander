package smartin.offhander.fabric;

import dev.architectury.platform.Platform;
import net.fabricmc.api.EnvType;
import smartin.offhander.Offhander;
import net.fabricmc.api.ModInitializer;

public class OffhanderFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Offhander.init();
        if(Platform.getEnv() == EnvType.CLIENT){
            OffhanderFabricClient.setup();
        }
    }
}