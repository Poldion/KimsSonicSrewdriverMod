package net.pold.sonicscrewdriver.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pold.sonicscrewdriver.SonicScrewdriver;
import net.pold.sonicscrewdriver.block.custom.RedstoneAirBlock;
import net.pold.sonicscrewdriver.item.ModItems;
import net.pold.sonicscrewdriver.item.custom.SonicScrewdriverItem;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SonicScrewdriver.MODID);

    public static final RegistryObject<Block> REDSTONE_AIR = registerBlock("redstone_air", () -> new RedstoneAirBlock(BlockBehaviour.Properties.of().replaceable().noCollission().noLootTable().air().noOcclusion()));

    //.air

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}