package smartin.offhander.forge;

import net.minecraft.client.Minecraft;
import net.neoforged.fml.common.Mod;
import smartin.offhander.Offhander;

@Mod(Offhander.MOD_ID)
public class OffhanderForge {
    public OffhanderForge() {
        try{
            Minecraft.getInstance();
            OffhanderForgeClient.setup();
        }catch (RuntimeException e){

        }
    }
}