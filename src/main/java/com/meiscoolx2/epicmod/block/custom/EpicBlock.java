package com.meiscoolx2.epicmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class EpicBlock extends Block {
    public EpicBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide()) {
            // schedule a tick 3 game ticks later
            level.scheduleTick(pos, this, 3);
        }

        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // send the delayed chat here
        for (ServerPlayer player : level.getPlayers(p -> p.blockPosition().equals(pos.above()))) {
            player.sendSystemMessage(Component.literal("ow no step me"));
        }

        // then destroy the block
        level.destroyBlock(pos, true); // true = drops
    }
}
