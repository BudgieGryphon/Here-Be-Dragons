package com.budgiegryphon.herebedragons.common.entities.dragons;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
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
    public void setState(byte state) {
        this.entityData.set(STATE, (state));
    }
    public byte getState() {
        return this.entityData.get(STATE);
    }
    /*
    STATES:
    0 = Neutral
    1 = Sleep
     */

    public void aiStep() {
        super.aiStep();
        this.sleepCondition();
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld pServerLevel, AgeableEntity pMate) {
        return null;
    }
}
