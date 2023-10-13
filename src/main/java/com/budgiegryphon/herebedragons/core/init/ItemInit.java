package com.budgiegryphon.herebedragons.core.init;

import com.budgiegryphon.herebedragons.herebedragons;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ItemInit {
	public static final DeferredRegister<Item> ITEMS  = DeferredRegister.create(ForgeRegistries.ITEMS,
			herebedragons.MOD_ID);
	public static final RegistryObject<Item> dragonleather = ITEMS.register("dragonleather", () -> new Item(new Item.Properties().tab(herebedragons.HBD_GROUP)));
	public static final RegistryObject<Item> dragonleatherscrap = ITEMS.register("dragonleatherscrap", () -> new Item(new Item.Properties().tab(herebedragons.HBD_GROUP)));
}
