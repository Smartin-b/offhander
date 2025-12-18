package smartin.offhander.forge;

import net.minecraftforge.fml.common.Mod;
import smartin.offhander.Offhander;

@Mod(Offhander.MOD_ID)
public class OffhanderForge {
    public OffhanderForge() {
        OffhanderForgeClient.setup();
    }
}