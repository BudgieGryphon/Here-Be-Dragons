package com.budgiegryphon.herebedragons.core.moditems;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class dragonfleshdessicated extends Item {
    public dragonfleshdessicated(Item.Properties properties) {super (properties); }

    public int getUseDuration(@NotNull ItemStack stack) {return 60; }
}

