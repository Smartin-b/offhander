package smartin.offhander.mixin;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import smartin.offhander.OffHanderClient;

@Mixin(KeyMapping.class)
public class KeyMappingMixin {

    @Inject(method = "Lnet/minecraft/client/KeyMapping;isDown()Z", at = @At("TAIL"), cancellable = true)
    private void injectMethod(CallbackInfoReturnable<Boolean> cir) {
        KeyMapping mapping = (KeyMapping) (Object) (this);
        if (Minecraft.getInstance() == null) {
            return;
        }
        if (mapping == Minecraft.getInstance().options.keyUse) {
            //cir.setReturnValue(cir.getReturnValueZ() || OffHanderClient.MAIN_HAND.isDown() || OffHanderClient.OFF_HAND.isDown());
        }
    }
}
