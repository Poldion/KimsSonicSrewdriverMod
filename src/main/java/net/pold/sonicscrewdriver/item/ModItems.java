package net.pold.sonicscrewdriver.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pold.sonicscrewdriver.SonicScrewdriver;
import net.pold.sonicscrewdriver.item.custom.SonicScrewdriverItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SonicScrewdriver.MODID);


    public static final RegistryObject<Item> SONICSCREWDRIVER = ITEMS.register("sonicscrewdriver",
            () -> new SonicScrewdriverItem(new Item.Properties().durability(100)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
