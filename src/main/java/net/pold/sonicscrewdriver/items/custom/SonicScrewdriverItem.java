package net.pold.sonicscrewdriver.items.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;

public class SonicScrewdriverItem extends Item {
    public SonicScrewdriverItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        if(!pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            BlockState stat = pContext.getLevel().getBlockState(positionClicked);

            // Get the direction of the clicked block face
            Direction direction = pContext.getClickedFace();

            // Find the next air block along that direction
            BlockPos nextAirBlock = positionClicked;
            while (pContext.getLevel().getBlockState(nextAirBlock).getBlock() != Blocks.AIR) {
                nextAirBlock = nextAirBlock.relative(direction);
            }

            // Replace the air block with a redstone block only if it is an air block
            BlockState nextBlockState = pContext.getLevel().getBlockState(nextAirBlock);
            if (nextBlockState.getBlock() == Blocks.AIR) {
                BlockState redstoneBlock = Blocks.REDSTONE_WALL_TORCH.defaultBlockState();
                pContext.getLevel().setBlockAndUpdate(nextAirBlock, redstoneBlock);
            }

            outputCoordinates(positionClicked, player);
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }




    private void outputCoordinates(BlockPos blockPos, Player player) {
        player.sendSystemMessage(Component.literal(blockPos.getX() + "," + blockPos.getY() + "," + blockPos.getZ()));
    }
}