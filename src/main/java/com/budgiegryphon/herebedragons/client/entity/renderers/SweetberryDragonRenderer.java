package com.budgiegryphon.herebedragons.client.entity.renderers;

import com.budgiegryphon.herebedragons.core.entities.dragons.SweetberryDragonEntity;
import com.budgiegryphon.herebedragons.client.entity.animators.SweetberryDragonAnimator;
import mod.azure.azurelib.render.entity.AzEntityRenderer;
import mod.azure.azurelib.render.entity.AzEntityRendererConfig;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;



public class SweetberryDragonRenderer extends AzEntityRenderer<SweetberryDragonEntity> {

    private static final ResourceLocation MODEL = new ResourceLocation("herebedragons", "geo/entities/berrydragon/berrydragon.geo.json");
    private static final ResourceLocation TEXTURE = new ResourceLocation("herebedragons","textures/entities/berrydragon/sweetberry.png");
    private static final ResourceLocation BABYTEXTURE = new ResourceLocation("herebedragons","textures/entities/berrydragon/sweetbaby.png");

    public SweetberryDragonRenderer(EntityRendererProvider.Context context) {
        super(AzEntityRendererConfig.<SweetberryDragonEntity>builder(MODEL, TEXTURE).setAnimatorProvider(SweetberryDragonAnimator::new)
                .setRenderType(RenderType.entityCutoutNoCull(TEXTURE)).build(), context);
    }

}
