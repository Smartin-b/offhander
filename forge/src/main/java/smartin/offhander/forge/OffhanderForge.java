package smartin.offhander.forge;

import net.minecraft.client.Minecraft;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforgespi.Environment;
import smartin.offhander.Offhander;

@Mod(Offhander.MOD_ID)
public class OffhanderForge {
    public OffhanderForge() {
        if(Environment.get().getDist().isClient()){
            Minecraft.getInstance();
            OffhanderForgeClient.setup();
        }
    }
}