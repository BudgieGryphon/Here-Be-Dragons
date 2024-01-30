package com.budgiegryphon.herebedragons.client.entity.render;

import com.budgiegryphon.herebedragons.herebedragons;
import com.budgiegryphon.herebedragons.client.entity.model.SweetberryDragonModel;
import com.budgiegryphon.herebedragons.common.entities.dragons.SweetberryDragonEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.azure.azurelib.cache.object.BakedGeoModel;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SweetberryDragonRenderer extends GeoEntityRenderer<SweetberryDragonEntity> {
    public SweetberryDragonRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SweetberryDragonModel());
        this.shadowRadius = 0.1f;
    }
    @Override
    public void preRender(MatrixStack poseStack, SweetberryDragonEntity entity, BakedGeoModel model, IRenderTypeBuffer bufferSource, IVertexBuilder buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (entity.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender,partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
