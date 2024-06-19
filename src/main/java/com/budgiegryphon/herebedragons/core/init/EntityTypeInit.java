package com.budgiegryphon.herebedragons.core.init;

import com.budgiegryphon.herebedragons.common.entities.dragons.GallimimeEntity;
import com.budgiegryphon.herebedragons.herebedragons;
import com.budgiegryphon.herebedragons.common.entities.dragons.SweetberryDragonEntity;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class EntityTypeInit {
	public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, herebedragons.MOD_ID);

	public static final RegistryObject<EntityType<SweetberryDragonEntity>> SWEETBERRYDRAGON_ENTITY =
			ENTITY_TYPES.register("sweetberry_dragon", () -> EntityType.Builder.of(SweetberryDragonEntity::new,
					EntityClassification.AMBIENT).sized(0.5f, 0.5f).build(new ResourceLocation(herebedragons.MOD_ID, "sweetberry_dragon").toString()));
	public static final RegistryObject<EntityType<GallimimeEntity>> GALLIMIME_ENTITY =
			ENTITY_TYPES.register("gallimime_wyvern", () -> EntityType.Builder.of(GallimimeEntity::new,
					EntityClassification.AMBIENT).sized(2.0f, 2.0f).build(new ResourceLocation(herebedragons.MOD_ID, "gallimime_wyvern").toString()));
	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}
	public static void setAttributes(EntityAttributeCreationEvent event) {
		event.put(EntityTypeInit.SWEETBERRYDRAGON_ENTITY.get(), SweetberryDragonEntity.createAttributes().build());
		event.put(EntityTypeInit.GALLIMIME_ENTITY.get(), GallimimeEntity.createAttributes().build());
	}
}
