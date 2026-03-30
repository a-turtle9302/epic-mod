package com.meiscoolx2.epicmod.util;

import com.meiscoolx2.epicmod.EpicMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class ItemTags {
        public static final TagKey<Item> TURTLE_REPAIR = createTag("turtle_repair");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(EpicMod.MOD_ID, name));
        }
    }
}