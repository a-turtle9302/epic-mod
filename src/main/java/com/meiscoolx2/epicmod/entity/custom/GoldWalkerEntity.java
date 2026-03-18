package com.meiscoolx2.epicmod.entity.custom;

import com.meiscoolx2.epicmod.entity.ModEntityTypes;
import com.meiscoolx2.epicmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class GoldWalkerEntity extends PathfinderMob {
    public GoldWalkerEntity(Level world) {
        this(ModEntityTypes.GOLDWALKER, world);
    }

    public GoldWalkerEntity(EntityType<? extends GoldWalkerEntity> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createCubeAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5)
                .add(Attributes.TEMPT_RANGE, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.3);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TemptGoal(this, 1, Ingredient.of(ModItems.SUSPICIOUS_SUBSTANCE), false));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 4));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide()) {
            if (this.getDeltaMovement().horizontalDistanceSqr() > 0.001) {
                BlockPos pos = this.blockPosition().below();
                BlockState state = this.level().getBlockState(pos);

                if (state.isAir()) return;

                this.level().setBlockAndUpdate(pos, Blocks.GOLD_BLOCK.defaultBlockState());
            }
        }
    }
}