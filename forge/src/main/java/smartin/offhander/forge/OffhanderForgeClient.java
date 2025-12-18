package smartin.offhander.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import smartin.offhander.OffHanderClient;
import smartin.offhander.Offhander;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT,modid = Offhander.MOD_ID)
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
