package com.budgiegryphon.herebedragons.core.init;

import com.budgiegryphon.herebedragons.core.entities.dragons.SweetberryDragonEntity;
import com.budgiegryphon.herebedragons.herebedragons;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypeInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, herebedragons.MOD_ID);

    public static final RegistryObject<EntityType<SweetberryDragonEntity>> SWEETBERRYDRAGON_ENTITY =
            ENTITY_TYPES.register("sweetberry_dragon", () -> EntityType.Builder.of(SweetberryDragonEntity::new,
                    MobCategory.CREATURE).sized(0.5f, 0.5f).build("sweetberry_dragon"));


}
