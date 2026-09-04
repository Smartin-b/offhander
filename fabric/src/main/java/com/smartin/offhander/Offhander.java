package com.smartin.offhander;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;

public class Offhander implements ModInitializer {

    @Override
    public void onInitialize() {
        if (EnvType.CLIENT == FabricLoader.getInstance().getEnvironmentType()) {
            initClient();
        }
    }

    @Environment(EnvType.CLIENT)
    public static void initClient() {
        OffHanderClient.LOG.info("Hello Fabric world!");
        OffHanderClient.MAPPINGS.forEach((id, map) -> {
            KeyBindingHelper.registerKeyBinding(map);
        });
    }
}
