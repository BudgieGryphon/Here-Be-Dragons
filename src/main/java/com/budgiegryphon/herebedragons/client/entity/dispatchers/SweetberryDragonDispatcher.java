package com.budgiegryphon.herebedragons.client.entity.dispatchers;

import com.budgiegryphon.herebedragons.common.entities.dragons.SweetberryDragonEntity;
import mod.azure.azurelib.rewrite.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.rewrite.animation.play_behavior.AzPlayBehaviors;

public class SweetberryDragonDispatcher {
    private static final AzCommand IDLE_COMMAND = AzCommand.create(
            "base_controller",
            "idle",
            AzPlayBehaviors.LOOP
    );

    private static final AzCommand FLY_COMMAND = AzCommand.create(
            "base_controller",
            "fly",
            AzPlayBehaviors.LOOP
    );

    private static final AzCommand SLEEP_COMMAND = AzCommand.create(
            "base_controller",
            "sleep",
            AzPlayBehaviors.LOOP
    );

    private final SweetberryDragonEntity sweetberry_dragon;

    public SweetberryDragonDispatcher(SweetberryDragonEntity animatable) {
        this.sweetberry_dragon = animatable;
    }

    public void idle() {
        IDLE_COMMAND.sendForEntity(sweetberry_dragon);
    }

    public void fly() {
        FLY_COMMAND.sendForEntity(sweetberry_dragon);
    }

    public void sleep() {
        SLEEP_COMMAND.sendForEntity(sweetberry_dragon);
    }
}

