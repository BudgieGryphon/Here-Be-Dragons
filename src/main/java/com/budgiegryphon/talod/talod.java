package com.budgiegryphon.talod;


import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.budgiegryphon.talod.core.init.ItemInit;
import com.budgiegryphon.talod.client.entity.render.SweetberryDragonRenderer;
import com.budgiegryphon.talod.common.entities.dragons.SweetberryDragonEntity;
import com.budgiegryphon.talod.core.init.EntityTypeInit;
import com.budgiegryphon.talod.core.init.FoodInit;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(talod.MOD_ID)
public class talod
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID =  "talod";
    public static final ItemGroup TALOD_GROUP = new TalodGroup("talodtab");
    public talod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setupClient);
        modEventBus.addListener(this::registerEntityAttributes);
        ItemInit.ITEMS.register(modEventBus);
        FoodInit.ITEMS.register(modEventBus);
        EntityTypeInit.ENTITY_TYPES.register(modEventBus);
        GeckoLibMod.DISABLE_IN_DEV = true;
        GeckoLib.initialize();
        MinecraftForge.EVENT_BUS.register(this);

    }

    public static class TalodGroup extends ItemGroup {
        public TalodGroup(String label) {
            super("label");
        }

        @Override
        public ItemStack makeIcon() {
            return FoodInit.cookedsweetberrydrg.get().getDefaultInstance();
        }
    }

    @SubscribeEvent
    public void setupClient(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.SWEETBERRYDRAGON_ENTITY.get(), SweetberryDragonRenderer::new);
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityTypeInit.SWEETBERRYDRAGON_ENTITY.get(), SweetberryDragonEntity.createAttributes().build());
    }
}

