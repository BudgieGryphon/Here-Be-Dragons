package com.budgiegryphon.talod.core.init;
import com.budgiegryphon.talod.talod;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FoodInit {
    public static final DeferredRegister<Item> ITEMS  = DeferredRegister.create(ForgeRegistries.ITEMS,
            talod.MOD_ID);
    public static final RegistryObject<Item> sweetberrydrgitem = ITEMS.register
            ("sweetberrydrgitem", () -> new com.budgiegryphon.talod.common.moditems.sweetberrydrgitem(
                    new Item.Properties().tab(talod.TALOD_GROUP)
                            .food(new Food.Builder().nutrition(3).saturationMod(0.5f)
                                    .build()).stacksTo(16)));
    public static final RegistryObject<Item> cookedsweetberrydrg = ITEMS.register(
            "cookedsweetberrydrg", () -> new Item(new Item.Properties().tab(talod.TALOD_GROUP)
                    .food(new Food.Builder().nutrition(7).saturationMod(3f).build())));
}
