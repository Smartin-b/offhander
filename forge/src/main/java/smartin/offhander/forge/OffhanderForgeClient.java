package smartin.offhander.forge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;
import smartin.offhander.OffHanderClient;
import smartin.offhander.Offhander;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD,value = Dist.CLIENT,modid = Offhander.MOD_ID)
public class OffhanderForgeClient {
    public static void setup() {
        //NeoForge.EVENT_BUS.addListener(OffhanderForgeClient::registerBindings);
    }


    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {

        OffHanderClient.MAPPINGS.forEach((id, mapping) -> {
            event.register(mapping);
        });
    }
}
