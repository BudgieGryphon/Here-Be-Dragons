package com.budgiegryphon.herebedragons.client.entity.render;

import com.budgiegryphon.herebedragons.client.entity.model.GallimimeModel;
import com.budgiegryphon.herebedragons.common.entities.dragons.GallimimeEntity;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class GallimimeRenderer extends GeoEntityRenderer<GallimimeEntity> {
    public GallimimeRenderer(EntityRendererManager renderManager) {
        super(renderManager, new GallimimeModel());

        this.shadowRadius = 0.1f;
    }
}
