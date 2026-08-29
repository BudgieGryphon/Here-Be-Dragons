package com.budgiegryphon.herebedragons.core.init;

import com.budgiegryphon.herebedragons.core.moditems.*;
import com.budgiegryphon.herebedragons.herebedragons;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, herebedragons.MOD_ID);

    //berrydragons

    public static final RegistryObject<Item> sweetberrydrgitem = ITEMS.register(
            "sweetberrydrg", () -> new sweetberrydrg(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5).saturationMod(2f).meat().build()
                    ).stacksTo(16)
            )
    );

    public static final RegistryObject<Item> deadsweetberrydrg = ITEMS.register(
            "deadsweetberrydrg", () -> new deadberrydrg(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5).saturationMod(2f).meat().build()
                    )
            )
    );

    public static final RegistryObject<Item> glowberrydrgitem = ITEMS.register(
            "glowberrydrg", () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5).saturationMod(2f).meat()
                            .effect(new MobEffectInstance(MobEffects.GLOWING, 180), 1.0F).build()
                    )
                    .stacksTo(16)
            )
    );

    public static final RegistryObject<Item> deadglowberrydrg = ITEMS.register(
            "deadglowberrydrg", () -> new deadberrydrg(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5).saturationMod(2f).meat()
                            .effect(new MobEffectInstance(MobEffects.GLOWING, 180), 1.0F).build()
                    )
            )
    );

    public static final RegistryObject<Item> babysweetberrydrgitem = ITEMS.register(
            "babysweetberrydrgitem", () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(0.5f).meat()
                            .effect(new MobEffectInstance(MobEffects.POISON, 60), 1.0F).build()
                    )
                    .stacksTo(16)
            )
    );

    public static final RegistryObject<Item> babyglowberrydrgitem = ITEMS.register(
            "babyglowberrydrgitem", () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(0.5f).meat()
                            .effect(new MobEffectInstance(MobEffects.POISON, 60), 1.0F).build()
                    )
                    .stacksTo(16)
            )
    );

    //various foods of various edibility

    public static final RegistryObject<Item> roastedsweetberrydrg = ITEMS.register(
            "roastedsweetberrydrg", () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(7).saturationMod(6f).meat().build()
                    )
            )
    );
    public static final RegistryObject<Item> roastedglowberrydrg = ITEMS.register(
            "roastedglowberrydrg", () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(7).saturationMod(6f).meat()
                            .effect(new MobEffectInstance(MobEffects.GLOWING, 40), 1.0F).build()
                    )
            )
    );

    public static final RegistryObject<Item> dragonfleshmorsel = ITEMS.register(
            "dragonfleshmorsel", () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1).saturationMod(0.5f).meat()
                            .effect(new MobEffectInstance(MobEffects.WEAKNESS, 120), 1.0F)
                            .effect(new MobEffectInstance(MobEffects.HUNGER, 80), 0.4F)
                            .effect(new MobEffectInstance(MobEffects.HARM, 2), 0.25F).build()
                    )
            )
    );

    public static final RegistryObject<Item> dragonfleshpiece = ITEMS.register(
            "dragonfleshpiece", () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(1f).meat()
                            .effect(new MobEffectInstance(MobEffects.WEAKNESS, 120), 1.0F)
                            .effect(new MobEffectInstance(MobEffects.HUNGER, 160, 1), 0.8F)
                            .effect(new MobEffectInstance(MobEffects.HARM, 2), 0.5F).build()
                    )
            )
    );

    public static final RegistryObject<Item> dragonfleshchunk = ITEMS.register(
            "dragonfleshchunk", () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4).saturationMod(2f).meat()
                            .effect(new MobEffectInstance(MobEffects.WEAKNESS, 120), 1.0F)
                            .effect(new MobEffectInstance(MobEffects.HUNGER, 240, 2), 0.8F)
                            .effect(new MobEffectInstance(MobEffects.HARM, 1), 1.0F).build()
                    )
            )
    );

    public static final RegistryObject<Item> cookeddragonfleshmorsel = ITEMS.register(
            "cookeddragonfleshmorsel", () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(3).saturationMod(1.5f).meat().build()
                    )
            )
    );

    public static final RegistryObject<Item> cookeddragonfleshpiece = ITEMS.register(
            "cookeddragonfleshpiece", () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(7).saturationMod(3.5f).meat().build()
                    )
            )
    );

    public static final RegistryObject<Item> cookeddragonfleshchunk = ITEMS.register(
            "cookeddragonfleshchunk", () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(15).saturationMod(8f).meat().build()
                    )
            )
    );

    public static final RegistryObject<Item> dragonfleshdessicated = ITEMS.register(
            "dragonfleshdessicated", () -> new dragonfleshdessicated(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(3).saturationMod(1.5f).meat()
                            .effect(new MobEffectInstance(MobEffects.WEAKNESS, 40), 1.0F).build()
                    )
            )
    );

    public static final RegistryObject<Item> dragonsblood = ITEMS.register(
            "dragonsblood", () -> new dragonsblood(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition( 1).saturationMod(0.5f).meat()
                            .effect(new MobEffectInstance(MobEffects.WEAKNESS, 600, 2), 1.0F)
                            .effect(new MobEffectInstance(MobEffects.HUNGER, 400, 2), 1.0F)
                            .effect(new MobEffectInstance(MobEffects.HARM, 1, 1), 1.0F).build()
                    )
                    .craftRemainder(Items.GLASS_BOTTLE)
            )
    );

    public static final RegistryObject<Item> dragonheart = ITEMS.register(
            "dragonheart", () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4).saturationMod(2f).meat()
                            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600, 3), 1.0F)
                            .effect(new MobEffectInstance(MobEffects.HUNGER, 240, 2), 0.8F)
                            .effect(new MobEffectInstance(MobEffects.HARM, 1), 1.0F).build()
                    )
            )
    );

    //NOT foods

    public static final RegistryObject<Item> dragonsight = ITEMS.register(
            "dragonsight", () -> new Item(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> dragonhide = ITEMS.register(
            "dragonhide", () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> dragonhidescrap = ITEMS.register(
            "dragonhidescrap", () -> new Item(new Item.Properties()
            )
    );
    public static final RegistryObject<Item> dragonhidesaddle = ITEMS.register(
            "dragonsaddle", () -> new Item(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> mushclumpcrimson = ITEMS.register(
            "mushclumpcrimson", () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> mushclumpwarped = ITEMS.register(
            "mushclumpwarped", () -> new Item(new Item.Properties())
    );

    //egge
    public static final RegistryObject<Item> sporedrakeegg = ITEMS.register(
            "sporedrakeegg", () -> new sporedrakeegg(new Item.Properties().stacksTo(1))
    );
    public static final RegistryObject<Item> gallikinegg = ITEMS.register(
            "gallikinegg", () -> new gallikinegg(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> hoardstokeregg = ITEMS.register(
            "hoardstokeregg", () -> new hoardstokeregg(new Item.Properties().stacksTo(1))
    );

}
