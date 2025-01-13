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
    protected static final DataParameter<Integer> TIMER = EntityDataManager.defineId(BaseDragonEntity.class, DataSerializers.INT);
    protected BaseDragonEntity(EntityType<? extends BaseDragonEntity> type, World worldIn) {
        super (type, worldIn);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, (byte)0);
        this.entityData.define(TIMER, 0);
    }
    public boolean sleepCondition() {
        return false;
    }
    public boolean usesTimer() {
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
    public void setTimer(int timer) {this.entityData.set(TIMER, timer);}
    public int getTimer() {
        return this.entityData.get(TIMER);
    }
    /*
    timer is used for anything done individually at random such as idle animations, also increments with some randomness
    */

    public void aiStep(){
        super.aiStep();
        if (this.usesTimer()) {
            int t = this.getTimer();
            if (t >= 1000 || t < 0) {
                this.setTimer(0);
            }
            else {
                t++;
                if (this.getRandom().nextInt(10) >= 9) {
                    t++;
                }
                this.setTimer(t);
            }
        }
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld pServerLevel, AgeableEntity pMate) {
        return null;
    }
}
