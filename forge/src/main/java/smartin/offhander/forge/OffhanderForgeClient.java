package smartin.offhander.forge;

import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import smartin.offhander.OffHanderClient;
import smartin.offhander.Offhander;

public class OffhanderForgeClient {
    public static void setup() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(new ClientModEvents());
    }


    public static class ClientModEvents {

        @SubscribeEvent
        public void registerBindings(RegisterKeyMappingsEvent event) {

            OffHanderClient.MAPPINGS.forEach((id, mapping) -> {
                Offhander.LOGGER.info("register keybindins");
                event.register(mapping);
            });
        }
    }
}
