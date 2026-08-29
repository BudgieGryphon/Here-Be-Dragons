package com.budgiegryphon.herebedragons.core.entities.dragons;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class BaseDragonEntity extends Animal {

    protected static final EntityDataAccessor<Byte> STATE = SynchedEntityData.defineId(BaseDragonEntity.class, EntityDataSerializers.BYTE);

    protected BaseDragonEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public boolean sleepCondition() {return false;}

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, (byte) 0);

    }

    public void setState(int state) {
        this.entityData.set(STATE, ((byte)state));
    }
    public byte getState() {
        return this.entityData.get(STATE);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}
