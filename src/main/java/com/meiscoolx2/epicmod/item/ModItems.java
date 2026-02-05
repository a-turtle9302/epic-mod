package com.meiscoolx2.epicmod.item;

import com.meiscoolx2.epicmod.EpicMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.function.Function;

public class ModItems {
    public static final Item SUSPICIOUS_SUBSTANCE = registerItem("suspicious_substance", Item::new, new Item.Properties());
    public static final Item KNOCKBACK_STICK = registerItem("knockback_stick", Item::new, new Item.Properties());


    public static <GenericItem extends Item> GenericItem registerItem(String name, Function<Item.Properties, GenericItem> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(EpicMod.MOD_ID, name));
        GenericItem item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    public static void registerModItems() {
        EpicMod.LOGGER.info("Registering Mod Items for " + EpicMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(itemGroup -> {
            itemGroup.accept(ModItems.SUSPICIOUS_SUBSTANCE);
            itemGroup.accept(ModItems.KNOCKBACK_STICK);
        });
    }
}
