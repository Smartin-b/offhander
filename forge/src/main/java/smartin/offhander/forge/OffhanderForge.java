package smartin.offhander.forge;

import net.minecraft.client.Minecraft;
import smartin.offhander.Offhander;
import net.minecraftforge.fml.common.Mod;

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