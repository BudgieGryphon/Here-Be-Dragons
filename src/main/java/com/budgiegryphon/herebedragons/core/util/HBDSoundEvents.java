package com.budgiegryphon.herebedragons.core.util;

import com.budgiegryphon.herebedragons.herebedragons;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HBDSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create
            (ForgeRegistries.SOUND_EVENTS, herebedragons.MOD_ID);
    public static final RegistryObject<SoundEvent> SWEETBERRY_AMBIENT = registerSoundEvent
            ("sweetberry_ambient");
    public static final RegistryObject<SoundEvent> SWEETBERRY_HURT = registerSoundEvent
            ("sweetberry_hurt");
    public static final RegistryObject<SoundEvent> SWEETBERRY_DIE = registerSoundEvent
            ("sweetberry_die");


    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(herebedragons.MOD_ID, name)));
    }
    public static void register(IEventBus bus) {
        SOUND_EVENTS.register(bus);
    }
}
