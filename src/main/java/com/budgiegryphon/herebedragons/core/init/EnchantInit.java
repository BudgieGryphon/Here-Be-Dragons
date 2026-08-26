package com.budgiegryphon.herebedragons.core.init;

import com.budgiegryphon.herebedragons.core.enchants.DragonsbaneEnchantment;
import com.budgiegryphon.herebedragons.herebedragons;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.world.item.enchantment.Enchantment.Rarity.RARE;

public class EnchantInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, herebedragons.MOD_ID);

    public static final RegistryObject<Enchantment> dragonsbane = ENCHANTMENTS.register("dragonsbane", () -> new DragonsbaneEnchantment(RARE, EquipmentSlot.MAINHAND));
}
