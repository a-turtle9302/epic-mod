package com.meiscoolx2.epicmod.block;

import com.meiscoolx2.epicmod.EpicMod;
import com.meiscoolx2.epicmod.block.custom.BounceulatorBlock;
import com.meiscoolx2.epicmod.block.custom.EpicBlock;
import com.meiscoolx2.epicmod.block.custom.QuarterBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.function.Function;

public class ModBlocks {

    public static final Block EPIC_BLOCK = registerBlock(
            "epic_block",
            EpicBlock::new,
            BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE),
            true
    );

    public static final Block GLASS_DOOR = registerBlock(
            "glass_door",
            props -> new DoorBlock(ModBlockSetTypes.GLASSISH, props),
            BlockBehaviour.Properties.of()
                    .noOcclusion(),
            true
    );

    public static final Block BOUNCEULATOR = registerBlock(
            "bounceulator",
            BounceulatorBlock::new,
            BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .sound(SoundType.SLIME_BLOCK),
            true
    );

    public static final Block SPRUCE_QUARTER_BLOCK = registerBlock(
            "spruce_quarter_block",
            props -> new QuarterBlock(Blocks.SPRUCE_PLANKS.defaultBlockState(), props),
            BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .sound(SoundType.WOOD),
            true
    );

    public static final Block BLUE_TRAPDOOR = registerBlock(
            "blue_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.OAK, props),
            BlockBehaviour.Properties.of(),
            true
    );

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
        // idk how this works ask the fabric docs
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.setId(blockKey));

        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(EpicMod.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(EpicMod.MOD_ID, name));
    }

    public static void registerModBlocks() {
        EpicMod.LOGGER.info("Registering Mod Blocks for " + EpicMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register((itemGroup) -> {
            itemGroup.accept(ModBlocks.EPIC_BLOCK);
            itemGroup.accept(ModBlocks.GLASS_DOOR);
            itemGroup.accept(ModBlocks.SPRUCE_QUARTER_BLOCK);
            itemGroup.accept(ModBlocks.BLUE_TRAPDOOR);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register((itemGroup) -> {
            itemGroup.accept(ModBlocks.BOUNCEULATOR);
        });
    }
}
