package me.pixeldots.f1hands.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Shadow
    private Minecraft minecraft;

    private boolean hideGuiBackup;
    
    @Inject(at = @At("HEAD"), method = "renderItemInHand")
    private void renderItemInHandHead(CallbackInfo info) {
        this.hideGuiBackup = this.minecraft.options.hideGui;
        this.minecraft.options.hideGui = false;
    }

    @Inject(at = @At("Tail"), method = "renderItemInHand")
    private void renderItemInHandTail(CallbackInfo info) {
        this.minecraft.options.hideGui = this.hideGuiBackup;
    }
}
