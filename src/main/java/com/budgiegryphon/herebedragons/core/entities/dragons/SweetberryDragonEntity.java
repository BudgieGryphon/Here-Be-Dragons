package com.budgiegryphon.herebedragons.core.entities.dragons;

import com.budgiegryphon.herebedragons.client.entity.dispatchers.SweetberryDragonDispatcher;
import com.budgiegryphon.herebedragons.core.init.SoundInit;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class SweetberryDragonEntity extends BaseDragonEntity implements FlyingAnimal {
    public SweetberryDragonEntity(EntityType<? extends BaseDragonEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.dispatcher = new SweetberryDragonDispatcher(this);
    }
    public final SweetberryDragonDispatcher dispatcher;

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 5D)
                .add(Attributes.FOLLOW_RANGE, 10D);
    }

    @Override
    public boolean isFlying() {
        return false;
    }

    public void tick() {
        super.tick();
        dispatcher.idle();
    }

    protected SoundEvent getAmbientSound() {
        if (this.getState() == 1 && this.onGround()) {
            return null;
        }
        else return SoundInit.SWEETBERRY_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundInit.SWEETBERRY_HURT.get();
    }
    protected SoundEvent getDeathSound() {
        return SoundInit.SWEETBERRY_DIE.get();
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}
