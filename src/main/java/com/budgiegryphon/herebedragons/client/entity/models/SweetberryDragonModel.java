package com.budgiegryphon.herebedragons.client.entity.models;

import com.budgiegryphon.herebedragons.common.entities.dragons.SweetberryDragonEntity;

import mod.azure.azurelib.constant.DataTickets;
import mod.azure.azurelib.core.animatable.model.CoreGeoBone;
import mod.azure.azurelib.core.animation.AnimationState;
import mod.azure.azurelib.model.GeoModel;
import mod.azure.azurelib.model.data.EntityModelData;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;


public class SweetberryDragonModel extends GeoModel<SweetberryDragonEntity> {
    private static final ResourceLocation model = new ResourceLocation("herebedragons","geo/berrydragon.geo.json");
    private static final ResourceLocation texture = new ResourceLocation("herebedragons","textures/entities/sweetberrydragon/sweetberry.png");
    private static final ResourceLocation babytexture = new ResourceLocation("herebedragons","textures/entities/sweetberrydragon/sweetbaby.png");
    private static final ResourceLocation animation = new ResourceLocation("herebedragons","animations/berrydragon.animation.json");

    @Override
    public ResourceLocation getModelResource(SweetberryDragonEntity object) {
        return model;
    }
    @Override
    public ResourceLocation getTextureResource(SweetberryDragonEntity object) {
        if (object.isBaby()) {
            return babytexture;
        }
        else {
            return texture;
        }
    }
    @Override
    public ResourceLocation getAnimationResource(SweetberryDragonEntity object) {
        return animation;
    }
    @Override
    public void setCustomAnimations(SweetberryDragonEntity animatable, long instanceId, AnimationState<SweetberryDragonEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        final CoreGeoBone head = getAnimationProcessor().getBone("neck");
        final EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        if (head != null) {
            float currentRotX = head.getRotX();
            float currentRotY = head.getRotY();
            head.setRotX((Vector3f.XP.rotation(entityData.headPitch() * ((float) Math.PI / 180F)).i()) + currentRotX);
            head.setRotY((Vector3f.YP.rotation(entityData.netHeadYaw() * ((float) Math.PI / 180F)).j()) + currentRotY);
        }
    }
}
