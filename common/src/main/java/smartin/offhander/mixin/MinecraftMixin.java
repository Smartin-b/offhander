package smartin.offhander.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import smartin.offhander.OffHanderClient;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "handleKeybinds", at = @At("HEAD"))
    private void injectMethod(CallbackInfo ci) {
        //OffHanderClient.clientTick(Minecraft.getInstance());
    }

    @Redirect(method = "handleKeybinds",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;releaseUsingItem(Lnet/minecraft/world/entity/player/Player;)V"
            )
    )
    private void redirectKeyUp(MultiPlayerGameMode instance, Player player) {
        if (!(OffHanderClient.MAIN_HAND.isDown() || OffHanderClient.OFF_HAND.isDown())) {
            instance.releaseUsingItem(player);
        }
    }

    @Redirect(
            method = "startUseItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/InteractionHand;values()[Lnet/minecraft/world/InteractionHand;")
    )
    private InteractionHand[] redirectInteractionHandValues() {
        if (((KeyMappingAccessor) Minecraft.getInstance().options.keyUse).offhanderIsDown()) {
            OffHanderClient.ACTIVE_HANDS = new InteractionHand[]{InteractionHand.MAIN_HAND, InteractionHand.OFF_HAND};
        } else if (OffHanderClient.MAIN_HAND.isDown()) {
            OffHanderClient.ACTIVE_HANDS = new InteractionHand[]{InteractionHand.MAIN_HAND};
        } else if (OffHanderClient.OFF_HAND.isDown()) {
            OffHanderClient.ACTIVE_HANDS = new InteractionHand[]{InteractionHand.OFF_HAND};
        }
        return OffHanderClient.ACTIVE_HANDS;
    }
}
