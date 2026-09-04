package com.smartin.offhander;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@Mod(OffHanderClient.MOD_ID)
public class Offhander {

    public static final String MOD_ID = "offhander";

    public Offhander(IEventBus eventBus) {
        //NeoForge.EVENT_BUS.register(this);
    }

    @EventBusSubscriber(
            value = Dist.CLIENT,
            modid = MOD_ID
    )
    public static class ClientSetup{
        @SubscribeEvent
        public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
            event.registerCategory(OffHanderClient.CATEGORY);
            OffHanderClient.MAPPINGS.forEach((id, map) -> {
                event.register(map);
            });
        }
    }
}