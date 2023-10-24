package com.budgiegryphon.herebedragons.common.entities.ai;

import java.util.EnumSet;

import com.budgiegryphon.herebedragons.common.entities.dragons.BaseDragonEntity;
import net.minecraft.entity.ai.goal.Goal;

public class DragonLookRandomlyGoal extends Goal {
    private final BaseDragonEntity entity;

    protected final float probability;
    protected final int time;
    private double relX;
    private double relZ;
    private int fullLookTime;


    public DragonLookRandomlyGoal(BaseDragonEntity entity, float probability, int time) {
        this.entity = entity;
        this.probability = probability;
        this.time = time;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    public boolean canUse() {
        return this.entity.getRandom().nextFloat() < probability && this.entity.getState() == 0 && this.entity.getNavigation().isDone() && this.entity.isOnGround();
    }

    public boolean canContinueToUse() {
        return this.fullLookTime > 0;
    }

    public void start() {
        this.entity.setState((byte) 0);
        double d0 = (Math.PI * 2D) * this.entity.getRandom().nextDouble();
        this.relX = Math.cos(d0);
        this.relZ = Math.sin(d0);
        this.fullLookTime = time + this.entity.getRandom().nextInt(20);
    }
    public void stop() {
        this.entity.setState((byte) 2);
    }

    public void tick() {
        this.entity.getLookControl().setLookAt(this.entity.getX() + this.relX, this.entity.getEyeY(), this.entity.getZ() + this.relZ);
        --this.fullLookTime;
    }
}