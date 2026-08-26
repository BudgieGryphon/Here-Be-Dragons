package com.budgiegryphon.herebedragons.client.setup;

import com.budgiegryphon.herebedragons.client.entity.renderers.SweetberryDragonRenderer;
import com.budgiegryphon.herebedragons.core.init.EntityTypeInit;
import com.budgiegryphon.herebedragons.herebedragons;
import net.minecraft.client.renderer.entity.EntityRenderers;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = herebedragons.MOD_ID, value = Dist.CLIENT)
public class RenderRegistry {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(EntityTypeInit.SWEETBERRYDRAGON_ENTITY.get(), SweetberryDragonRenderer::new);
    }
}
