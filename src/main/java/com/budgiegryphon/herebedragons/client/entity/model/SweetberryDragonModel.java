package com.budgiegryphon.herebedragons.client.entity.model;

import com.budgiegryphon.herebedragons.herebedragons;
import com.budgiegryphon.herebedragons.common.entities.dragons.SweetberryDragonEntity;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SweetberryDragonModel extends AnimatedGeoModel<SweetberryDragonEntity>{

    @Override
    public ResourceLocation getAnimationFileLocation(SweetberryDragonEntity animatable) {
        return new ResourceLocation(herebedragons.MOD_ID, "animations/berrydragon.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(SweetberryDragonEntity object) {
        return new ResourceLocation(herebedragons.MOD_ID, "geo/berrydragon.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SweetberryDragonEntity object) {
        return new ResourceLocation(herebedragons.MOD_ID, "textures/entities/sweetberry.png");
    }

}
