package com.budgiegryphon.herebedragons.client.entity.animators;

import com.budgiegryphon.herebedragons.common.entities.dragons.SweetberryDragonEntity;
import com.sun.istack.internal.NotNull;
import mod.azure.azurelib.constant.DataTickets;
import mod.azure.azurelib.core.animatable.model.CoreGeoBone;
import mod.azure.azurelib.core.animation.AnimationState;
import mod.azure.azurelib.model.data.EntityModelData;
import mod.azure.azurelib.rewrite.animation.cache.AzBoneCache;
import mod.azure.azurelib.rewrite.animation.controller.AzAnimationController;
import mod.azure.azurelib.rewrite.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.rewrite.animation.impl.AzEntityAnimator;
import mod.azure.azurelib.rewrite.model.AzBone;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

import java.util.Optional;

public class SweetberryDragonAnimator extends AzEntityAnimator<SweetberryDragonEntity>{
    private static final ResourceLocation ANIMATIONS = new ResourceLocation(
            "herebedragons",
            "animations/entities/berrydragon.animation/json"
    );

    @Override
    public void registerControllers(AzAnimationControllerContainer<SweetberryDragonEntity> animationControllerContainer) {
        animationControllerContainer.add(
                AzAnimationController.builder(this, "base_controller")
                        .build()
        );
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(SweetberryDragonEntity animatable) {
        return ANIMATIONS;
    }

    @Override
    public void setCustomAnimations(SweetberryDragonEntity animatable, float partialTicks) {
        super.setCustomAnimations(animatable, partialTicks);
        AzBoneCache boneCache = this.context().boneCache();
        Optional<AzBone> head = boneCache.getBakedModel().getBone("neck");

        if (head.isPresent()) {
            float currentRotX = head.getRotX();
            float currentRotY = head.getRotY();
            //head.setRotX((Vector3f.XP.rotation(figure out how to get head pitch again * ((float) Math.PI / 180F)).i()) + currentRotX);
            //head.setRotY((Vector3f.YP.rotation(figure out how to get head yaw again * ((float) Math.PI / 180F)).j()) + currentRotY);
        }
    }

}
