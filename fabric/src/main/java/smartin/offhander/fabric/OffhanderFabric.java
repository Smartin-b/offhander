package smartin.offhander.fabric;

import com.mojang.authlib.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.client.Minecraft;
import smartin.offhander.Offhander;
import net.fabricmc.api.ModInitializer;

public class OffhanderFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Offhander.init();
        try{
            Minecraft.getInstance();
            OffhanderFabricClient.setup();
        }catch (RuntimeException e){

        }
    }
}