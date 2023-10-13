package com.budgiegryphon.herebedragons.common.moditems;

import com.budgiegryphon.herebedragons.common.entities.dragons.SweetberryDragonEntity;
import com.budgiegryphon.herebedragons.core.init.EntityTypeInit;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class sweetberrydrgitem extends Item{

    public sweetberrydrgitem(Item.Properties p_i48487_1_) {
        super(p_i48487_1_);
    }
    public ActionResultType useOn(ItemUseContext here) {
        World world = here.getLevel();
        ItemStack itemstack = here.getItemInHand();
        if (!world.isClientSide) {
            BlockPos blockpos = here.getClickedPos();
            SweetberryDragonEntity entity = EntityTypeInit.SWEETBERRYDRAGON_ENTITY.get().create(world);
            if (itemstack.hasCustomHoverName()) {
                entity.setCustomName(itemstack.getHoverName());
            }

            entity.absMoveTo(blockpos.getX() + 0.5, blockpos.getY() + 1, blockpos.getZ() + 0.5);

            world.addFreshEntity(entity);
            itemstack.shrink(1);
        }
        return ActionResultType.PASS;
    }
}
