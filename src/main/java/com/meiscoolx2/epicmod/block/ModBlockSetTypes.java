package com.meiscoolx2.epicmod.block;

import com.meiscoolx2.epicmod.EpicMod;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModBlockSetTypes {
    public static final BlockSetType GLASSISH = BlockSetTypeBuilder
            .copyOf(BlockSetType.IRON)
            .openableByHand(true)
            .openableByWindCharge(true)
            .buttonActivatedByArrows(true)
            .soundGroup(SoundType.GLASS)
            .build(Identifier.fromNamespaceAndPath(EpicMod.MOD_ID, "glassish"));
}
