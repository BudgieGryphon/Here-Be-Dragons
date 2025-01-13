package com.budgiegryphon.herebedragons.client.entity.model;

import com.budgiegryphon.herebedragons.common.entities.dragons.SporedrakeEntity;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.ResourceLocation;

public class SporedrakeModel extends GeoModel<SporedrakeEntity> {
    private static final ResourceLocation model = new ResourceLocation("herebedragons","geo/sporedrake.geo.json");
    private static final ResourceLocation texture = new ResourceLocation("herebedragons","textures/entities/sporedrake/sporedrakefull.png");
    private static final ResourceLocation animation = new ResourceLocation("herebedragons","animations/sporedrake.animation.json");
    @Override
    public ResourceLocation getModelResource(SporedrakeEntity sporedrakeEntity) {
        return model;
    }

    @Override
    public ResourceLocation getTextureResource(SporedrakeEntity sporedrakeEntity) {
        return texture;
    }

    @Override
    public ResourceLocation getAnimationResource(SporedrakeEntity sporedrakeEntity) {
        return animation;
    }
}
