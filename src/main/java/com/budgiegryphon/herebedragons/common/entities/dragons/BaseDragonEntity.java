package com.budgiegryphon.herebedragons.common.entities.dragons;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class BaseDragonEntity extends TameableEntity {
    protected static final DataParameter<Byte> STATE = EntityDataManager.defineId(BaseDragonEntity.class, DataSerializers.BYTE);
    protected BaseDragonEntity(EntityType<? extends BaseDragonEntity> type, World worldIn) {
        super (type, worldIn);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, (byte)0);
    }
    public boolean sleepCondition() {
        return false;
    }
    //I'm not sure if this is a potential lag causer but just in case try not to run it too often
    public boolean isOverSolidObject() {
        if (this.isOnGround()) {
            return true;
        }
        double i = this.getY();
        if (i < 0) {
            return false;
        }

        while (i >= 0) {
            BlockPos posChecked = new BlockPos(this.getX(), i, this.getZ());
            BlockState checkedState = this.level.getBlockState(posChecked);
            Block blockChecked = checkedState.getBlock();
            if (blockChecked != Blocks.AIR) {
                return checkedState.entityCanStandOn(this.level, posChecked, this);
            }
            i--;
        }
        return false;
    }
    public void setState(int state) {
        this.entityData.set(STATE, ((byte)state));
    }
    public byte getState() {
        return this.entityData.get(STATE);
    }
    /*
    STATES:
    0 = Neutral
    1 = Sleep
    2 = Flight
     */


    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld pServerLevel, AgeableEntity pMate) {
        return null;
    }
}
