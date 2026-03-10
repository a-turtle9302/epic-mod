package com.meiscoolx2.epicmod.entity;

import com.meiscoolx2.epicmod.EpicMod;
import com.meiscoolx2.epicmod.entity.custom.GrimmyEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntityTypes {
    public static final EntityType<GrimmyEntity> GRIMMY = register(
            "grimmy",
            EntityType.Builder.<GrimmyEntity>of(GrimmyEntity::new, MobCategory.MISC)
                    .sized(0.5f, 1f)
    );

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(EpicMod.MOD_ID, name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void registerModEntityTypes() {
        EpicMod.LOGGER.info("Registering Mod EntityTypes for " + EpicMod.MOD_ID);
    }

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(GRIMMY, GrimmyEntity.createCubeAttributes());
    }
}
