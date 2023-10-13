package com.budgiegryphon.herebedragons.client.entity.render;

import com.budgiegryphon.herebedragons.herebedragons;
import com.budgiegryphon.herebedragons.client.entity.model.SweetberryDragonModel;
import com.budgiegryphon.herebedragons.common.entities.dragons.SweetberryDragonEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SweetberryDragonRenderer extends GeoEntityRenderer<SweetberryDragonEntity>{
    public SweetberryDragonRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SweetberryDragonModel());
        this.shadowRadius = 0.1f;
    }
    @Override
    public RenderType getRenderType(SweetberryDragonEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        if (animatable.isBaby()) {
            stack.scale(0.5F, 0.5F, 0.5F);
        }
        return RenderType.entityTranslucent(this.getTextureLocation(animatable));
    }
    @Override
    public ResourceLocation getTextureLocation(SweetberryDragonEntity entity) {
        return new ResourceLocation(herebedragons.MOD_ID, "textures/entities/sweetberry.png");
    }

}
