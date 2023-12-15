package com.budgiegryphon.herebedragons.common.entities.ai;

import com.budgiegryphon.herebedragons.common.entities.dragons.BaseDragonEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNavigator;

import java.util.EnumSet;

public class LandOnHeadGoal extends Goal {
    private final BaseDragonEntity entity;
    private PlayerEntity player;
    private final PathNavigator navigation;
    private final double detectDistance;
    private int time;

    public LandOnHeadGoal(BaseDragonEntity entity, float distance) {
        this.entity = entity;
        this.navigation = entity.getNavigation();
        this.detectDistance = distance;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        if (this.entity.getRandom().nextFloat() <= 0.02F && this.entity.getState() != 1 && this.entity.getNavigation().isDone()) {
            this.player = this.entity.level.getNearestPlayer(this.entity.getX(), this.entity.getEyeY(), this.entity.getZ(), detectDistance, true);
            return this.player != null && this.entity.canSee(player) && !this.player.isVehicle();
        }
        return false;
    }
    public boolean canContinueToUse() {
        return this.player != null && this.entity.distanceToSqr(this.player) > detectDistance;
    }

    public void start() {
        this.navigation.moveTo(player, 1.0);
    }
    public void stop() {
        this.navigation.stop();
        super.stop();
    }
    public void tick() {

    }
}
