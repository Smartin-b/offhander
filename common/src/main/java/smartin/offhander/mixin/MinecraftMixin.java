package smartin.offhander.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import smartin.offhander.OffHanderClient;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

@Mixin(Minecraft.class)
public interface MinecraftMixin {
    @Inject(method = "handleKeybinds", at = @At("HEAD"))
    private void injectMethod(CallbackInfo ci) {
        OffHanderClient.clientTick(Minecraft.getInstance());
    }

    /*
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

     */

    @Inject(method = "handleKeybinds",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;releaseUsingItem(Lnet/minecraft/world/entity/player/Player;)V",
                    shift = BEFORE
            )
    )
    private void redirectKeyUp(CallbackInfo ci) {
    }

    @Inject(method = "handleKeybinds",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;releaseUsingItem(Lnet/minecraft/world/entity/player/Player;)V",
                    shift = AFTER
            )
    )
    private void redirectKeyUpAfter(CallbackInfo ci) {
    }
}
