package smartin.offhander.mixin;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
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
        if (mapping.equals(Minecraft.getInstance().options.keyUse)) {
            if(cir.getReturnValue()){
                OffHanderClient.ACTIVE_HANDS = new InteractionHand[]{InteractionHand.MAIN_HAND, InteractionHand.OFF_HAND};
            }else if (OffHanderClient.MAIN_HAND.isDown()) {
                OffHanderClient.ACTIVE_HANDS = new InteractionHand[]{InteractionHand.MAIN_HAND};
                cir.setReturnValue(true);
            } else if (OffHanderClient.OFF_HAND.isDown()) {
                OffHanderClient.ACTIVE_HANDS = new InteractionHand[]{InteractionHand.OFF_HAND};
                cir.setReturnValue(true);
            }
        }
    }

}
