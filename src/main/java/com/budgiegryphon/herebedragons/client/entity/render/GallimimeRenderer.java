package com.budgiegryphon.herebedragons.client.entity.render;

import com.budgiegryphon.herebedragons.client.entity.model.GallimimeModel;
import com.budgiegryphon.herebedragons.common.entities.dragons.GallimimeEntity;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import mod.azure.azurelib.renderer.layer.AutoGlowingGeoLayer;
import mod.azure.azurelib.renderer.layer.GeoRenderLayer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class GallimimeRenderer extends GeoEntityRenderer<GallimimeEntity> {
    public GallimimeRenderer(EntityRendererManager renderManager) {
        super(renderManager, new GallimimeModel());
        addRenderLayer(new AutoGlowingGeoLayer<>(this));
        addRenderLayer(new CustomLayer<>(this));
        this.shadowRadius = 0.1f;
    }
}
