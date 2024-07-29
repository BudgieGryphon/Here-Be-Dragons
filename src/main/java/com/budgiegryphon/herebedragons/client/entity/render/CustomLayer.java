package com.budgiegryphon.herebedragons.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mod.azure.azurelib.cache.object.BakedGeoModel;
import mod.azure.azurelib.core.animatable.GeoAnimatable;
import mod.azure.azurelib.renderer.GeoRenderer;
import mod.azure.azurelib.renderer.layer.GeoRenderLayer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class CustomLayer<T extends GeoAnimatable> extends GeoRenderLayer<T> {
    public CustomLayer(GeoRenderer<T> renderer) {
        super(renderer);
    }
    protected RenderType getRenderType(T animatable){
        return CustomLayerTexture.getRenderType(this.getTextureResource(animatable));
    }

    public void render(MatrixStack poseStack, T animatable, BakedGeoModel bakedModel, RenderType renderType, IRenderTypeBuffer bufferSource, IVertexBuilder buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType customRenderType = this.getRenderType(animatable);
        this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, customRenderType, bufferSource.getBuffer(customRenderType), partialTick, 100, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
