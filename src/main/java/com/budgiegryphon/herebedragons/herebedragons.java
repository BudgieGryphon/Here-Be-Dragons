package com.budgiegryphon.herebedragons;


import com.budgiegryphon.herebedragons.core.util.HBDSoundEvents;
import com.budgiegryphon.herebedragons.client.util.ClientUtils;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.budgiegryphon.herebedragons.core.init.ItemInit;
import com.budgiegryphon.herebedragons.core.init.EntityTypeInit;
import com.budgiegryphon.herebedragons.core.init.FoodInit;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(herebedragons.MOD_ID)
public class herebedragons
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID =  "herebedragons";
    public static final ItemGroup HBD_GROUP = new HBDGroup("herebedragonstab");
    public herebedragons() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(ClientUtils::setupClient);
        modEventBus.addListener(EntityTypeInit::setAttributes);
        ItemInit.ITEMS.register(modEventBus);
        FoodInit.ITEMS.register(modEventBus);
        EntityTypeInit.ENTITY_TYPES.register(modEventBus);
        HBDSoundEvents.SOUND_EVENTS.register(modEventBus);
        GeckoLib.initialize();
        MinecraftForge.EVENT_BUS.register(this);

    }

    public static class HBDGroup extends ItemGroup {
        public HBDGroup(String label) {
            super(label);
        }

        @Override
        public ItemStack makeIcon() {
            return FoodInit.roastedsweetberrydrg.get().getDefaultInstance();
        }
    }


}

