package com.budgiegryphon.herebedragons.core.moditems;

import com.budgiegryphon.herebedragons.core.entities.dragons.SweetberryDragonEntity;
import com.budgiegryphon.herebedragons.core.init.EntityTypeInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;


public class sweetberrydrg extends Item {

    public sweetberrydrg(Properties properties) {super (properties);}

    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide) {
            ItemStack stack = context.getItemInHand();
            BlockPos pos = context.getClickedPos();
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (!(blockentity instanceof CampfireBlockEntity)) {
                SweetberryDragonEntity entity = EntityTypeInit.SWEETBERRYDRAGON_ENTITY.get().create(level);
                assert entity != null;
                if(stack.hasCustomHoverName()) {
                    entity.setCustomName(stack.getHoverName());
                }
                entity.setPos(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
                level.addFreshEntity(entity);
                stack.shrink(1);
            }
        }
        return InteractionResult.PASS;
    }
}
