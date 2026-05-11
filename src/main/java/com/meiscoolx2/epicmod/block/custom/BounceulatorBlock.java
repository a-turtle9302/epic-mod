package com.meiscoolx2.epicmod.block.custom;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public class BounceulatorBlock extends SlimeBlock {
    public BounceulatorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void updateEntityMovementAfterFallOn(BlockGetter level, Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.updateEntityMovementAfterFallOn(level, entity);
        } else {
            Vec3 vec3 = entity.getDeltaMovement();

            if (vec3.y < 0.0) {
                double multiplier = 1.35; // hello everybody my name is multiplier
                entity.setDeltaMovement(vec3.x, -vec3.y * multiplier, vec3.z);
            }
        }
    }
}
