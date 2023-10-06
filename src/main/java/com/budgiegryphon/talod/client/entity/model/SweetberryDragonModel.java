package com.budgiegryphon.talod.client.entity.model;

import com.budgiegryphon.talod.talod;
import com.budgiegryphon.talod.common.entities.dragons.SweetberryDragonEntity;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SweetberryDragonModel extends AnimatedGeoModel<SweetberryDragonEntity>{

    @Override
    public ResourceLocation getAnimationFileLocation(SweetberryDragonEntity animatable) {
        return new ResourceLocation(talod.MOD_ID, "animations/berrydragon.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(SweetberryDragonEntity object) {
        return new ResourceLocation(talod.MOD_ID, "geo/berrydragon.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SweetberryDragonEntity object) {
        return new ResourceLocation(talod.MOD_ID, "textures/entities/sweetberry.png");
    }

}
