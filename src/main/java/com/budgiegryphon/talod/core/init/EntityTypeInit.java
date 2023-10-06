package com.budgiegryphon.talod.core.init;

import com.budgiegryphon.talod.talod;
import com.budgiegryphon.talod.common.entities.dragons.SweetberryDragonEntity;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class EntityTypeInit {
	public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, talod.MOD_ID);

	public static final RegistryObject<EntityType<SweetberryDragonEntity>> SWEETBERRYDRAGON_ENTITY =
			ENTITY_TYPES.register("sweetberry_dragon", () -> EntityType.Builder.of(SweetberryDragonEntity::new,
					EntityClassification.AMBIENT).sized(0.5f, 0.5f).build(new ResourceLocation(talod.MOD_ID, "sweetberry_dragon").toString()));

	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}
}
