package com.budgiegryphon.herebedragons.core.entities.dragons;

import com.budgiegryphon.herebedragons.client.entity.dispatchers.SweetberryDragonDispatcher;
import com.budgiegryphon.herebedragons.core.init.ItemInit;
import com.budgiegryphon.herebedragons.core.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;

import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
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
        return !this.onGround();
    }

    public void tick() {
        super.tick();
        dispatcher.idle();
    }


    //yoink
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if(!this.level().isClientSide) {
            if (isFood(stack)) {
                return super.mobInteract(player, hand);
            }
            ItemStack stack1 = new ItemStack(ItemInit.sweetberrydrgitem.get());
            if(isBaby()) {
                stack1 = new ItemStack(ItemInit.babysweetberrydrgitem.get());
            }
            if(hasCustomName()) {
                stack1.setHoverName(getCustomName());
            }
            if(!stack.isEmpty()) {
                Containers.dropItemStack(level(), getX(), getY(), getZ(), stack1);
            }
            else {
                player.setItemInHand(hand, stack1);
            }
            remove(RemovalReason.DISCARDED);
        }
        return InteractionResult.sidedSuccess(this.level().isClientSide);
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

    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.SWEET_BERRIES;
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
    }

    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }
    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
    }
    public void makeStuckInBlock(BlockState state, Vec3 speed) {}

    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return size.height * 0.5F;
    }
}
