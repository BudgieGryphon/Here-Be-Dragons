package com.budgiegryphon.herebedragons.client.entity.dispatchers;

import com.budgiegryphon.herebedragons.core.entities.dragons.SweetberryDragonEntity;
import mod.azure.azurelib.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.animation.play_behavior.AzPlayBehaviors;

public class SweetberryDragonDispatcher {

    private static final AzCommand IDLEANIM = AzCommand.create(
            "base_controller", "animation.berrydragon.idle", AzPlayBehaviors.LOOP);

    private final SweetberryDragonEntity sweetberry_dragon;

    public SweetberryDragonDispatcher(SweetberryDragonEntity animatable) {
        this.sweetberry_dragon = animatable;
    }

    public void idle() {
        IDLEANIM.sendForEntity(sweetberry_dragon);
    }
}
