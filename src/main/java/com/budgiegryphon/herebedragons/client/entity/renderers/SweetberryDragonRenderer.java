package com.budgiegryphon.herebedragons.client.entity.renderers;

import com.budgiegryphon.herebedragons.common.entities.dragons.SweetberryDragonEntity;
import com.budgiegryphon.herebedragons.client.entity.animators.SweetberryDragonAnimator;
import mod.azure.azurelib.rewrite.render.AzRendererPipelineContext;
import mod.azure.azurelib.rewrite.render.entity.AzEntityRenderer;
import mod.azure.azurelib.rewrite.render.entity.AzEntityRendererConfig;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SweetberryDragonRenderer extends AzEntityRenderer<SweetberryDragonEntity> {

    private static final ResourceLocation MODEL = new ResourceLocation("herebedragons","geo/berrydragon.geo.json");
    private static final ResourceLocation TEXTURE = new ResourceLocation("herebedragons","textures/entities/sweetberrydragon/sweetberry.png");
    private static final ResourceLocation BABYTEXTURE = new ResourceLocation("herebedragons","textures/entities/sweetberrydragon/sweetbaby.png");



    public SweetberryDragonRenderer(EntityRendererManager renderManager) {
        super(
                AzEntityRendererConfig.builder(SweetberryDragonRenderer::modelLocation, SweetberryDragonRenderer::textureLocation))
                        .setAnimatorProvider(SweetberryDragonAnimator::new)
                        //.setPrerenderEntry(contextPipeline -> {
                        //makeBabyModel(contextPipeline);
                        //return contextPipeline;
                        //})
                        .build(),
                renderManager
        );
    }

    public static ResourceLocation textureLocation(SweetberryDragonEntity entity) {
        if (entity.isBaby()){
            return BABYTEXTURE;
        }

        return TEXTURE;
    }
    public static ResourceLocation modelLocation(SweetberryDragonEntity entity) {
        return MODEL;
    }

    private static void makeBabyModel(AzRendererPipelineContext<SweetberryDragonEntity> contextPipeline) {
        if(contextPipeline.animatable().isBaby()) {
            contextPipeline.poseStack().scale(0.5f, 0.5f, 0.5f);
        }
    }
}
