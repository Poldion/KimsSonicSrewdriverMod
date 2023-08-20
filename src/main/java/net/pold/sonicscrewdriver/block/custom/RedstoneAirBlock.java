package net.pold.sonicscrewdriver.block.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PoweredBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RedstoneAirBlock extends PoweredBlock {

    public RedstoneAirBlock(Properties properties) {
        super(properties);
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.empty();
    }
    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        // Erstelle einen Executor Service mit einem Thread
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        // Plane eine Aufgabe, die den Block nach einer Sekunde entfernt
        executor.schedule(() -> {
            // Setze den Block auf Luft
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            // Schließe den Executor Service
            executor.shutdown();
        }, 2, TimeUnit.SECONDS);
    }
}