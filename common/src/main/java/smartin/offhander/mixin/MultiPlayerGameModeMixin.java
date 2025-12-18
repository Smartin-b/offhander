package smartin.offhander.mixin;

import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import smartin.offhander.OffHanderClient;

@Mixin(MultiPlayerGameMode.class)
public abstract class MultiPlayerGameModeMixin {

    @Inject(method = "releaseUsingItem", at = @At("HEAD"), cancellable = true)
    private void injectMethod(CallbackInfo ci) {
        if ((OffHanderClient.MAIN_HAND.isDown() || OffHanderClient.OFF_HAND.isDown())) {
            ci.cancel();
        }
    }
}
