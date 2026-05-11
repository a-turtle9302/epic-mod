package com.meiscoolx2.epicmod.block.custom;

import com.mojang.math.OctahedralGroup;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

import java.util.Map;

public class QuarterBlock extends StairBlock {

    private static final VoxelShape BASE_STRAIGHT = Block.box(0, 0, 0, 16, 8, 8);
    private static final VoxelShape BASE_OUTER = Block.box(0, 0, 0, 8, 8, 8);
    private static final VoxelShape BASE_INNER = Shapes.or(
            Block.box(0, 0, 0, 16, 8, 8),  // long leg along Z
            Block.box(8, 0, 0, 16, 8, 16)  // short leg along X
    );

    private static final Map<Direction, VoxelShape> BOTTOM_STRAIGHT = Shapes.rotateHorizontal(BASE_STRAIGHT);
    private static final Map<Direction, VoxelShape> BOTTOM_OUTER = Shapes.rotateHorizontal(BASE_OUTER);
    private static final Map<Direction, VoxelShape> BOTTOM_INNER = Shapes.rotateHorizontal(BASE_INNER);

    private static final Map<Direction, VoxelShape> TOP_STRAIGHT = Shapes.rotateHorizontal(BASE_STRAIGHT, OctahedralGroup.INVERT_Y);
    private static final Map<Direction, VoxelShape> TOP_OUTER = Shapes.rotateHorizontal(BASE_OUTER, OctahedralGroup.INVERT_Y);
    private static final Map<Direction, VoxelShape> TOP_INNER = Shapes.rotateHorizontal(BASE_INNER, OctahedralGroup.INVERT_Y);

    public QuarterBlock(BlockState baseState, Properties properties) {
        super(baseState, properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(HALF, Half.BOTTOM)
                .setValue(SHAPE, StairsShape.STRAIGHT)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        boolean bottom = state.getValue(HALF) == Half.BOTTOM;
        Direction facing = state.getValue(FACING);

        Map<Direction, VoxelShape> map = switch (state.getValue(SHAPE)) {
            case STRAIGHT -> bottom ? BOTTOM_STRAIGHT : TOP_STRAIGHT;
            case OUTER_LEFT, OUTER_RIGHT -> bottom ? BOTTOM_OUTER : TOP_OUTER;
            case INNER_LEFT, INNER_RIGHT -> bottom ? BOTTOM_INNER : TOP_INNER;
        };

        Direction lookup = switch (state.getValue(SHAPE)) {
            case STRAIGHT, OUTER_LEFT, INNER_RIGHT -> facing;
            case INNER_LEFT -> facing.getCounterClockWise();
            case OUTER_RIGHT -> facing.getClockWise();
        };

        return map.get(lookup);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        return state.setValue(SHAPE, getStairsShape(state, context.getLevel(), context.getClickedPos()));
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks,
                                     BlockPos pos, Direction dir, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        state = super.updateShape(state, level, ticks, pos, dir, neighborPos, neighborState, random);
        return state.setValue(SHAPE, getStairsShape(state, level, pos)); // recompute for bending
    }

    private static StairsShape getStairsShape(BlockState state, BlockGetter level, BlockPos pos) {
        Direction direction = state.getValue(FACING);

        BlockState neighbor = level.getBlockState(pos.relative(direction));
        if (isStairs(neighbor) && state.getValue(HALF) == neighbor.getValue(HALF)) {
            Direction neighborFacing = neighbor.getValue(FACING);
            if (neighborFacing.getAxis() != direction.getAxis() && canTakeShape(state, level, pos, neighborFacing.getOpposite())) {
                return neighborFacing == direction.getCounterClockWise() ? StairsShape.OUTER_LEFT : StairsShape.OUTER_RIGHT;
            }
        }

        neighbor = level.getBlockState(pos.relative(direction.getOpposite()));
        if (isStairs(neighbor) && state.getValue(HALF) == neighbor.getValue(HALF)) {
            Direction neighborFacing = neighbor.getValue(FACING);
            if (neighborFacing.getAxis() != direction.getAxis() && canTakeShape(state, level, pos, neighborFacing)) {
                return neighborFacing == direction.getCounterClockWise() ? StairsShape.INNER_LEFT : StairsShape.INNER_RIGHT;
            }
        }

        return StairsShape.STRAIGHT;
    }

    private static boolean canTakeShape(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        BlockState neighbor = level.getBlockState(pos.relative(face));
        return !isStairs(neighbor) || neighbor.getValue(FACING) != state.getValue(FACING)
                || neighbor.getValue(HALF) != state.getValue(HALF);
    }

    public static boolean isStairs(BlockState state) {
        return state.getBlock() instanceof StairBlock;
    }
}