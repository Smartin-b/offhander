package smartin.offhander;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import smartin.offhander.mixin.MinecraftAccessor;

import java.util.HashMap;
import java.util.Map;

import static smartin.offhander.Offhander.LOGGER;
import static smartin.offhander.Offhander.MOD_ID;

public class OffHanderClient {
    public static final Map<ResourceLocation, KeyMapping> MAPPINGS = new HashMap<>();
    public static final KeyMapping MAIN_HAND = register(ResourceLocation.tryParse(MOD_ID + ":mainhand"), new KeyMapping(MOD_ID + ".mainhand", -1, MOD_ID + ".keybinds"));
    public static final KeyMapping OFF_HAND = register(ResourceLocation.tryParse(MOD_ID + ":offhand"), new KeyMapping(MOD_ID + ".offhand", InputConstants.Type.MOUSE, 4, MOD_ID + ".keybinds"));

    public static boolean wasOffHandLastDown = false;
    public static boolean wasMainHandLastDown = false;
    public static boolean wasUseKeyLastDown = false;

    public static void clientTick(Minecraft client) {
        LOGGER.info("client tick");
        LocalPlayer player = client.player;
        if (player != null) {
            if (player.isUsingItem()) {
                if (!OFF_HAND.isDown() && wasOffHandLastDown) {
                    client.gameMode.releaseUsingItem(player);
                    wasOffHandLastDown = OFF_HAND.isDown();
                    return;
                }
                wasOffHandLastDown = OFF_HAND.isDown();
                if (!MAIN_HAND.isDown() && wasMainHandLastDown) {
                    client.gameMode.releaseUsingItem(player);
                    wasMainHandLastDown = MAIN_HAND.isDown();
                    return;
                }
                wasMainHandLastDown = MAIN_HAND.isDown();
                while (OFF_HAND.consumeClick()) {
                }
                while (MAIN_HAND.consumeClick()) {
                }
            }else{
                while (OFF_HAND.consumeClick()) {
                    startUseItem(client, InteractionHand.OFF_HAND);
                }
                while (MAIN_HAND.consumeClick()) {
                    startUseItem(client, InteractionHand.MAIN_HAND);
                }
            }
            if (OFF_HAND.isDown() && ((MinecraftAccessor) client).getRightClickDelay() == 0 && !player.isUsingItem()) {
                startUseItem(client, InteractionHand.OFF_HAND);
            }
            if (MAIN_HAND.isDown() && ((MinecraftAccessor) client).getRightClickDelay() == 0 && !player.isUsingItem()) {
                startUseItem(client, InteractionHand.MAIN_HAND);
            }
        }
    }

    public static KeyMapping register(ResourceLocation location, KeyMapping mapping) {
        MAPPINGS.put(location, mapping);
        return mapping;
    }

    private static void startUseItem(Minecraft minecraft, InteractionHand interactionHand) {
        LOGGER.info("use item");
        if (!minecraft.gameMode.isDestroying()) {
            ((MinecraftAccessor) minecraft).setRightClickDelay(4);
            if (!minecraft.player.isHandsBusy()) {
                if (minecraft.hitResult == null) {
                    LOGGER.warn("Null returned as 'hitResult', this shouldn't happen!");
                }

                InteractionHand[] var1 = InteractionHand.values();
                int var2 = var1.length;
                ItemStack itemStack = minecraft.player.getItemInHand(interactionHand);
                if (!itemStack.isItemEnabled(minecraft.level.enabledFeatures())) {
                    return;
                }

                if (minecraft.hitResult != null) {
                    switch (minecraft.hitResult.getType()) {
                        case ENTITY:
                            EntityHitResult entityHitResult = (EntityHitResult) minecraft.hitResult;
                            Entity entity = entityHitResult.getEntity();
                            if (!minecraft.level.getWorldBorder().isWithinBounds(entity.blockPosition())) {
                                return;
                            }

                            InteractionResult interactionResult = minecraft.gameMode.interactAt(minecraft.player, entity, entityHitResult, interactionHand);
                            if (!interactionResult.consumesAction()) {
                                interactionResult = minecraft.gameMode.interact(minecraft.player, entity, interactionHand);
                            }

                            if (interactionResult.consumesAction()) {
                                if (interactionResult.shouldSwing()) {
                                    minecraft.player.swing(interactionHand);
                                }

                                return;
                            }
                            break;
                        case BLOCK:
                            BlockHitResult blockHitResult = (BlockHitResult) minecraft.hitResult;
                            int i = itemStack.getCount();
                            InteractionResult interactionResult2 = minecraft.gameMode.useItemOn(minecraft.player, interactionHand, blockHitResult);
                            if (interactionResult2.consumesAction()) {
                                if (interactionResult2.shouldSwing()) {
                                    minecraft.player.swing(interactionHand);
                                    if (!itemStack.isEmpty() && (itemStack.getCount() != i || minecraft.gameMode.hasInfiniteItems())) {
                                        minecraft.gameRenderer.itemInHandRenderer.itemUsed(interactionHand);
                                    }
                                }

                                return;
                            }

                            if (interactionResult2 == InteractionResult.FAIL) {
                                return;
                            }
                    }
                }

                if (!itemStack.isEmpty()) {
                    InteractionResult interactionResult3 = minecraft.gameMode.useItem(minecraft.player, interactionHand);
                    if (interactionResult3.consumesAction()) {
                        if (interactionResult3.shouldSwing()) {
                            minecraft.player.swing(interactionHand);
                        }

                        minecraft.gameRenderer.itemInHandRenderer.itemUsed(interactionHand);
                        return;
                    }

                }
            }
        }
    }
}
