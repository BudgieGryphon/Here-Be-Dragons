package com.budgiegryphon.herebedragons.common.entities.ai;

import com.budgiegryphon.herebedragons.common.entities.dragons.BaseDragonEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.EntityPredicates;

import java.util.EnumSet;

public class DragonLookAtGoal extends Goal {
    protected final BaseDragonEntity entity;
    protected Entity lookAt;
    protected final float lookDistance;
    private int fulllookTime;
    protected final float probability;
    protected final int time;
    protected final EntityPredicate lookAtContext;
//update to match DragonLookRandomly soon
    public DragonLookAtGoal(BaseDragonEntity entity, float distance, float probability, int time) {
        this.entity = entity;
        this.lookDistance = distance;
        this.probability = probability;
        this.time = time;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.MOVE));
        this.lookAtContext = (new EntityPredicate()).range((double)distance).allowSameTeam().allowInvulnerable().allowNonAttackable().selector((p_220715_1_) -> EntityPredicates.notRiding(entity).test(p_220715_1_));
    }
    public boolean canUse() {
        if (this.entity.getRandom().nextFloat() >= this.probability || this.entity.getState() == 1) {
            return false;
        } else {
            if (this.entity.getTarget() != null) {
                this.lookAt = this.entity.getTarget();
            }
            this.lookAt = this.entity.level.getNearestLoadedEntity(LivingEntity.class, this.lookAtContext, this.entity, this.entity.getX(), this.entity.getEyeY(), this.entity.getZ(), this.entity.getBoundingBox().inflate((double) this.lookDistance, 3.0D, (double) this.lookDistance));
            return this.lookAt != null;
        }
    }
    public boolean canContinueToUse() {
        if (!this.lookAt.isAlive()) {
            return false;
        } else if (this.entity.distanceToSqr(this.lookAt) > (double)(this.lookDistance * this.lookDistance)) {
            return false;
        } else {
            return this.fulllookTime > 0;
        }
    }
    public void start() {
        this.fulllookTime = time + this.entity.getRandom().nextInt(40);
        this.entity.setState((byte)0);
    }
    public void stop() {
        this.lookAt = null;
    }


    public void tick() {
        this.entity.getLookControl().setLookAt(this.lookAt.getX(), this.entity.getEyeY(), this.lookAt.getZ());
        --this.fulllookTime;
    }
}
