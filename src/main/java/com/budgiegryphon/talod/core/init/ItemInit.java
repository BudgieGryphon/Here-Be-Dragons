package com.budgiegryphon.talod.core.init;

import com.budgiegryphon.talod.talod;

import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ItemInit {
	public static final DeferredRegister<Item> ITEMS  = DeferredRegister.create(ForgeRegistries.ITEMS,
			talod.MOD_ID);
	public static final RegistryObject<Item> dragonleather = ITEMS.register("dragonleather", () -> new Item(new Item.Properties().tab(talod.TALOD_GROUP)));
	public static final RegistryObject<Item> dragonleatherscrap = ITEMS.register("dragonleatherscrap", () -> new Item(new Item.Properties().tab(talod.TALOD_GROUP)));
}
