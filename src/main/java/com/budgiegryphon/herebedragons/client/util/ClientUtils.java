package com.budgiegryphon.herebedragons.client.util;

import com.budgiegryphon.herebedragons.client.entity.renderers.SweetberryDragonRenderer;
import com.budgiegryphon.herebedragons.core.init.EntityTypeInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
public class ClientUtils {
    @SubscribeEvent
    public static void setupClient(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.SWEETBERRYDRAGON_ENTITY.get(), SweetberryDragonRenderer::new);

    }
}
