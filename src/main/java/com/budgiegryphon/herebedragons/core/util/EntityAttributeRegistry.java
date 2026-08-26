package com.budgiegryphon.herebedragons.core.util;

import com.budgiegryphon.herebedragons.core.entities.dragons.SweetberryDragonEntity;
import com.budgiegryphon.herebedragons.core.init.EntityTypeInit;
import com.budgiegryphon.herebedragons.herebedragons;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = herebedragons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityAttributeRegistry {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityTypeInit.SWEETBERRYDRAGON_ENTITY.get(), SweetberryDragonEntity.createAttributes().build());
    }
}
