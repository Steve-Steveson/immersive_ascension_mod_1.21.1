package net.steveson.immersiveascension;

import blusunrize.immersiveengineering.api.EnumMetals;
import blusunrize.immersiveengineering.common.register.IECreativeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeColor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.steveson.immersiveascension.block.ModBlocks;
import net.steveson.immersiveascension.item.ModItems;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ImmersiveAscensionMod.MOD_ID)
public class ImmersiveAscensionMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "immersive_ascension";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public ImmersiveAscensionMod(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        for (DeferredHolder<CreativeModeTab, ? extends CreativeModeTab> tab : IECreativeTabs.REGISTER.getEntries()){
            if (event.getTabKey() == tab.getKey()) {
                event.accept(ModBlocks.INSULATING_GLASS_STAIRS);
                event.accept(ModBlocks.SLAG_GLASS_STAIRS);
                event.accept(ModBlocks.SLAG_GLASS_SLAB);
                for (EnumMetals m : EnumMetals.values()) {
                    if (!m.isVanillaMetal()) {
                        event.accept(ModBlocks.STORAGE_STAIRS.get(m));
                    }
                    event.accept(ModBlocks.SHEETMETAL_STAIRS.get(m));
                }
                for (DyeColor dye : DyeColor.values()) {
                    event.accept(ModBlocks.COLORED_SHEETMETAL_STAIRS.get(dye));
                }
                event.accept(ModBlocks.COKE_STAIRS);
                event.accept(ModBlocks.COKEBRICK_STAIRS);
                event.accept(ModBlocks.BLASTBRICK_STAIRS);
                event.accept(ModBlocks.BLASTBRICK_REINFORCED_STAIRS);
                event.accept(ModBlocks.ALLOYBRICK_STAIRS);
                event.accept(ModBlocks.CUT_URANIUM_BLOCK);
                event.accept(ModBlocks.CUT_URANIUM_STAIRS);
                event.accept(ModBlocks.CUT_URANIUM_SLAB);
                event.accept(ModBlocks.PILLAR_URANIUM_BLOCK);
                event.accept(ModBlocks.PILLAR_URANIUM_STAIRS);
                event.accept(ModBlocks.PILLAR_URANIUM_SLAB);
            }
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
