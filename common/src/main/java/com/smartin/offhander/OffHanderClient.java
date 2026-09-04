package com.smartin.offhander;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class OffHanderClient {

	public static final String MOD_ID = "offhander";
	public static final String MOD_NAME = "Offhander";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
	public static final Map<Identifier, KeyMapping> MAPPINGS = new HashMap<>();
	public static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MOD_ID, "category"));
	public static final KeyMapping MAIN_HAND = register(Identifier.tryParse(MOD_ID + ":mainhand"), new KeyMapping(MOD_ID + ".mainhand", -1, CATEGORY));
	public static final KeyMapping OFF_HAND = register(Identifier.tryParse(MOD_ID + ":offhand"), new KeyMapping(MOD_ID + ".offhand", InputConstants.Type.MOUSE, 4, CATEGORY));
	public static InteractionHand[] ACTIVE_HANDS = new InteractionHand[]{};

	public static KeyMapping register(Identifier location, KeyMapping mapping) {
		MAPPINGS.put(location, mapping);
		return mapping;
	}
}