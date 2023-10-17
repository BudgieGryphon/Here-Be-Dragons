package com.budgiegryphon.herebedragons.common.entities.ai;


import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

import java.util.EnumSet;

public class DragonSleepGoal extends Goal {
    private final MobEntity entity;
    protected boolean canSleep;
    public DragonSleepGoal(MobEntity entity, boolean condition) {
        this.entity = entity;
        this.canSleep = condition;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }
    public boolean canContinueToUse() {
        return !this.entity.isVehicle() && this.canSleep;
    }
    public boolean canUse() {
        //add isOnGround when dragon look control is finished
        return !this.entity.isVehicle() && this.canSleep && !this.entity.isAggressive();
    }
    public void start() {
        this.entity.getNavigation().stop();
        this.entity.setDeltaMovement(Vector3d.ZERO);
        this.entity.startSleeping(new BlockPos(this.entity.getX(), this.entity.getY(), this.entity.getZ()));
    }
    public void stop() {
        this.entity.stopSleeping();
    }
}
