package smartin.offhander.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import smartin.offhander.OffHanderClient;

@Mixin(net.minecraft.client.multiplayer.MultiPlayerGameMode.class)
public interface MultiPlayerGameMode {

    @Inject(method = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;releaseUsingItem(Lnet/minecraft/world/entity/player/Player;)V", at = @At("HEAD"), cancellable = true)
    private void injectMethod(CallbackInfo ci) {
        if ((OffHanderClient.MAIN_HAND.isDown() || OffHanderClient.OFF_HAND.isDown())) {
            ci.cancel();
        }
    }
}
