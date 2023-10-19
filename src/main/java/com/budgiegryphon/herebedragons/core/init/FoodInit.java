package com.budgiegryphon.herebedragons.core.init;
import com.budgiegryphon.herebedragons.herebedragons;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FoodInit {
    public static final DeferredRegister<Item> ITEMS  = DeferredRegister.create(ForgeRegistries.ITEMS,
            herebedragons.MOD_ID);
    public static final RegistryObject<Item> sweetberrydrgitem = ITEMS.register
            ("sweetberrydrgitem", () -> new com.budgiegryphon.herebedragons.common.moditems.sweetberrydrgitem(
                    new Item.Properties().tab(herebedragons.HBD_GROUP)
                            .food(new Food.Builder().nutrition(5).saturationMod(2f).meat()
                                    .build()).stacksTo(16)));
    public static final RegistryObject<Item> babysweetberrydrgitem = ITEMS.register
            ("babysweetberrydrgitem", () -> new com.budgiegryphon.herebedragons.common.moditems.babysweetberrydrgitem(
                    new Item.Properties().tab(herebedragons.HBD_GROUP)
                            .food(new Food.Builder().nutrition(3).saturationMod(0.5f).meat()
                                    .effect(() -> new EffectInstance(Effects.CONFUSION, 60), 1.0F)
                                    .effect(() -> new EffectInstance(Effects.POISON, 60), 0.5F).build()).stacksTo(16)));
    public static final RegistryObject<Item> roastedsweetberrydrg = ITEMS.register(
            "roastedsweetberrydrg", () -> new Item(new Item.Properties().tab(herebedragons.HBD_GROUP)
                    .food(new Food.Builder().nutrition(7).saturationMod(6f).meat().build())));
}
