package smartin.offhander.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import smartin.offhander.OffHanderClient;

@Mixin(KeyMapping.class)
public class KeyMappingMixin {

    @ModifyReturnValue(method = "Lnet/minecraft/client/KeyMapping;isDown()Z", at = @At("RETURN"))
    private boolean offhander$injectIsDownConsumeClick(boolean original) {
        KeyMapping mapping = (KeyMapping) (Object) (this);
        if (mapping.equals(Minecraft.getInstance().options.keyUse)) {
            if(original){
                OffHanderClient.ACTIVE_HANDS = new InteractionHand[]{InteractionHand.MAIN_HAND, InteractionHand.OFF_HAND};
            }else if (OffHanderClient.MAIN_HAND.isDown()) {
                OffHanderClient.ACTIVE_HANDS = new InteractionHand[]{InteractionHand.MAIN_HAND};
               return true;
            } else if (OffHanderClient.OFF_HAND.isDown()) {
                OffHanderClient.ACTIVE_HANDS = new InteractionHand[]{InteractionHand.OFF_HAND};
                return true;
            }
        }
        return original;
    }

    @ModifyReturnValue(method = "Lnet/minecraft/client/KeyMapping;consumeClick()Z", at = @At("RETURN"))
    private boolean offhander$injectConsumeRightClick(boolean original) {
        KeyMapping mapping = (KeyMapping) (Object) (this);
        if (mapping.equals(Minecraft.getInstance().options.keyUse) && !((KeyMappingAccessor)mapping).offhanderIsDown()) {
            if (OffHanderClient.MAIN_HAND.isDown()) {
                return OffHanderClient.MAIN_HAND.consumeClick();
            } else if (OffHanderClient.OFF_HAND.isDown()) {
                return OffHanderClient.OFF_HAND.consumeClick();
            }
        }
        return original;
    }

}
