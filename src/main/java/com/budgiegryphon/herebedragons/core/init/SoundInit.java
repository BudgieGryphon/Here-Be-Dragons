package com.budgiegryphon.herebedragons.core.init;

import com.budgiegryphon.herebedragons.herebedragons;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.resources.ResourceLocation;

public class SoundInit {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, herebedragons.MOD_ID);

    public static final RegistryObject<SoundEvent> SWEETBERRY_AMBIENT = registerSoundEvent("sweetberry_ambient");
    public static final RegistryObject<SoundEvent> SWEETBERRY_HURT = registerSoundEvent("sweetberry_hurt");
    public static final RegistryObject<SoundEvent> SWEETBERRY_DIE = registerSoundEvent("sweetberry_die");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(herebedragons.MOD_ID, name)));
    }
}
