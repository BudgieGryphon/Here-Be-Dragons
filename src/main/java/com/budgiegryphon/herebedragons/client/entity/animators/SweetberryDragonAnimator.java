package com.budgiegryphon.herebedragons.client.entity.animators;

import com.budgiegryphon.herebedragons.core.entities.dragons.SweetberryDragonEntity;
import mod.azure.azurelib.animation.controller.AzAnimationController;
import mod.azure.azurelib.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.animation.impl.AzEntityAnimator;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SweetberryDragonAnimator extends AzEntityAnimator<SweetberryDragonEntity> {
    private static final ResourceLocation ANIM = ResourceLocation.fromNamespaceAndPath("herebedragons", "animations/entities/berrydragon/berrydragon.animation.json");

    public void registerControllers(AzAnimationControllerContainer<SweetberryDragonEntity> animationControllerContainer) {
        animationControllerContainer.add(AzAnimationController.builder(this, "base_controller").build());
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(SweetberryDragonEntity sweetberryDragonEntity) {
        return ANIM;
    }
}
