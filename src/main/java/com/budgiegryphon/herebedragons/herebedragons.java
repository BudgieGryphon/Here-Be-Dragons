package com.budgiegryphon.herebedragons;

import com.budgiegryphon.herebedragons.core.init.EnchantInit;
import com.budgiegryphon.herebedragons.core.init.EntityTypeInit;
import com.budgiegryphon.herebedragons.core.init.ItemInit;
import com.budgiegryphon.herebedragons.core.init.HBDCreativeTab;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(herebedragons.MOD_ID)
public class herebedragons
{
    public static final String MOD_ID = "herebedragons";
    private static final Logger LOGGER = LogUtils.getLogger();

    public herebedragons(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        ItemInit.ITEMS.register(modEventBus);
        EntityTypeInit.ENTITY_TYPES.register(modEventBus);
        EnchantInit.ENCHANTMENTS.register(modEventBus);
        HBDCreativeTab.TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        

    }

}
