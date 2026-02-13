package smartin.offhander;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;

import java.util.HashMap;
import java.util.Map;

import static smartin.offhander.Offhander.MOD_ID;

public class OffHanderClient {
    public static final Map<ResourceLocation, KeyMapping> MAPPINGS = new HashMap<>();
    public static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(ResourceLocation.fromNamespaceAndPath(MOD_ID, "category"));
    public static final KeyMapping MAIN_HAND = register(ResourceLocation.tryParse(MOD_ID + ":mainhand"), new KeyMapping(MOD_ID + ".mainhand", -1, CATEGORY));
    public static final KeyMapping OFF_HAND = register(ResourceLocation.tryParse(MOD_ID + ":offhand"), new KeyMapping(MOD_ID + ".offhand", InputConstants.Type.MOUSE, 4, CATEGORY));
    public static InteractionHand[] ACTIVE_HANDS = new InteractionHand[]{};

    public static KeyMapping register(ResourceLocation location, KeyMapping mapping) {
        MAPPINGS.put(location, mapping);
        return mapping;
    }
}
