package com.budgiegryphon.herebedragons.client.entity.render;

import com.budgiegryphon.herebedragons.client.entity.model.SporedrakeModel;
import com.budgiegryphon.herebedragons.common.entities.dragons.SporedrakeEntity;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SporedrakeRenderer extends GeoEntityRenderer<SporedrakeEntity> {
    public SporedrakeRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SporedrakeModel());
        this.shadowRadius = 0.1f;
    }
}
