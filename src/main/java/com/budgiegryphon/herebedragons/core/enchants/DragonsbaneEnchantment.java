package com.budgiegryphon.herebedragons.core.enchants;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class DragonsbaneEnchantment extends Enchantment {

    public DragonsbaneEnchantment(Enchantment.Rarity pRarity, EquipmentSlot... pApplicableSlots) {

        super(pRarity, EnchantmentCategory.WEAPON, pApplicableSlots);
    }

    public int getMaxLevel() {
        return 5;
    }

    public boolean checkCompatibility(@NotNull Enchantment pEnch) {
        return !(pEnch instanceof DamageEnchantment);
    }

    //will work on later


}
