package com.budgiegryphon.herebedragons.client.entity.model;

import com.budgiegryphon.herebedragons.common.entities.dragons.GallimimeEntity;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.ResourceLocation;

public class GallimimeModel extends GeoModel<GallimimeEntity> {
    private static final ResourceLocation model = new ResourceLocation("herebedragons","geo/gallimime.geo.json");
    private static final ResourceLocation texture = new ResourceLocation("herebedragons","textures/entities/gallimime/gallimimewyvern.png");
    private static final ResourceLocation animation = new ResourceLocation("herebedragons","animations/gallimime.animation.json");

    @Override
    public ResourceLocation getModelResource(GallimimeEntity gallimimeEntity) {
        return model;
    }
    @Override
    public ResourceLocation getTextureResource(GallimimeEntity gallimimeEntity) {
        return texture;
    }
    @Override
    public ResourceLocation getAnimationResource(GallimimeEntity gallimimeEntity) {
        return animation;
    }
}
