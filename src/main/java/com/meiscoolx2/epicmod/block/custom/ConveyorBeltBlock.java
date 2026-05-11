package com.meiscoolx2.epicmod.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public class ConveyorBeltBlock extends HorizontalDirectionalBlock {

    public static final MapCodec<ConveyorBeltBlock> CODEC =
            simpleCodec(ConveyorBeltBlock::new);

    public ConveyorBeltBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    // Step 2: Handle placement so it faces the player
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        // Only push entities that are standing on top
        if (entity.getY() > pos.getY()) {
            Direction dir = state.getValue(FACING);

            Vec3 push = new Vec3(dir.getStepX(), 0, dir.getStepZ())
                    .normalize()
                    .scale(0.1); // conveyor speed

            entity.setDeltaMovement(entity.getDeltaMovement().add(push));
        }

        super.stepOn(level, pos, state, entity); // call parent in case it has logic
    }
}