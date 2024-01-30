package com.budgiegryphon.herebedragons.client.entity.model;

import com.budgiegryphon.herebedragons.herebedragons;
import com.budgiegryphon.herebedragons.common.entities.dragons.SweetberryDragonEntity;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.ResourceLocation;


public class SweetberryDragonModel extends GeoModel<SweetberryDragonEntity> {
    private static final ResourceLocation model = new ResourceLocation("herebedragons","geo/berrydragon.geo.json");
    private static final ResourceLocation texture = new ResourceLocation("herebedragons","textures/entities/sweetberry.png");
    private static final ResourceLocation babytexture = new ResourceLocation("herebedragons","textures/entities/sweetbaby.png");
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
}
