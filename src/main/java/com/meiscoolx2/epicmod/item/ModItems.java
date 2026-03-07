package com.meiscoolx2.epicmod.item;

import com.meiscoolx2.epicmod.EpicMod;
import com.meiscoolx2.epicmod.item.custom.SuspiciousSubstanceItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.function.Function;

public class ModItems {
    public static final Item SUSPICIOUS_SUBSTANCE = registerItem("suspicious_substance", SuspiciousSubstanceItem::new, new Item.Properties().stacksTo(100).food(ModFoodProperties.SUSPICIOUS_SUBSTANCE, ModFoodProperties.SUSPICIOUS_SUBSTANCE_EFFECT));
    public static final Item KNOCKBACK_STICK = registerItem("knockback_stick", Item::new, new Item.Properties()
            .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
            .rarity(Rarity.RARE)
            .attributes(ItemAttributeModifiers.builder().add(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(Identifier.fromNamespaceAndPath(EpicMod.MOD_ID, "stick_knockback"), 1000, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build()));
    public static final Item TURTLE = registerItem("turtle", Item::new, new Item.Properties().sword(ModToolMaterials.TURTLE, 3, -2.4f));

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
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(itemGroup -> {
            itemGroup.accept(ModItems.KNOCKBACK_STICK);
            itemGroup.accept(ModItems.TURTLE);
        });
    }
}
