package net.pold.sonicscrewdriver.items.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;

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

            outputCoordinates(positionClicked, player);
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    private void outputCoordinates(BlockPos blockPos, Player player) {
        player.sendSystemMessage(Component.literal(blockPos.getX() + "," + blockPos.getY() + "," + blockPos.getZ()));
    }
}
