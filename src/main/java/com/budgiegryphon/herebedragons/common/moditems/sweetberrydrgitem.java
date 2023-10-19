package com.budgiegryphon.herebedragons.common.moditems;

import com.budgiegryphon.herebedragons.common.entities.dragons.SweetberryDragonEntity;
import com.budgiegryphon.herebedragons.core.init.EntityTypeInit;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.CampfireTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class sweetberrydrgitem extends Item{

    public sweetberrydrgitem(Item.Properties properties) {
        super(properties);
    }
    public ActionResultType useOn(ItemUseContext pContext) {
        World world = pContext.getLevel();
        ItemStack itemstack = pContext.getItemInHand();
        if (!world.isClientSide) {
            BlockPos blockpos = pContext.getClickedPos();
            TileEntity tileentity = world.getBlockEntity(blockpos);
            if (!(tileentity instanceof CampfireTileEntity)) {
                SweetberryDragonEntity entity = EntityTypeInit.SWEETBERRYDRAGON_ENTITY.get().create(world);
                if (itemstack.hasCustomHoverName()) {
                    entity.setCustomName(itemstack.getHoverName());
                }

                entity.absMoveTo(blockpos.getX() + 0.5, blockpos.getY() + 1, blockpos.getZ() + 0.5);

                world.addFreshEntity(entity);
                itemstack.shrink(1);
            }
        }
        return ActionResultType.PASS;
    }
}
