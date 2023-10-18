package com.budgiegryphon.herebedragons.common.entities.ai;


import com.budgiegryphon.herebedragons.common.entities.dragons.BaseDragonEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.vector.Vector3d;

import java.util.EnumSet;

public class DragonSleepGoal extends Goal {
    private final BaseDragonEntity entity;
    public DragonSleepGoal(BaseDragonEntity entity) {
        this.entity = entity;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }
    public boolean canContinueToUse() {
        return !this.entity.isVehicle() && this.entity.sleepCondition();
    }
    public boolean canUse() {
        //add isOnGround when dragon look control is finished
        return !this.entity.isVehicle() && this.entity.sleepCondition() && !this.entity.isAggressive() && this.entity.getRandom().nextFloat() < 0.8F;
    }
    public void start() {
        this.entity.setState((byte) 1);
    }
    public void stop() {
        this.entity.setState((byte) 0);
    }
}
