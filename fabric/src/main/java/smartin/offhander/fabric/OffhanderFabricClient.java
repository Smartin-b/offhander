package smartin.offhander.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import smartin.offhander.OffHanderClient;

@Environment(EnvType.CLIENT)
public class OffhanderFabricClient {
    public static void setup() {
        OffHanderClient.MAPPINGS.forEach((id, mapping) -> {
            KeyBindingHelper.registerKeyBinding(mapping);
        });

    }
}
