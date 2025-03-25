
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package mine.moremining.init;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

import mine.moremining.MoreMiningMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MoreMiningModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoreMiningMod.MODID);

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			tabData.accept(MoreMiningModItems.DEEP_SEA_CRYSTAL.get());
			tabData.accept(MoreMiningModItems.INFERNAL_IRON_INGOT.get());
			tabData.accept(MoreMiningModItems.ETHER_SHARD.get());
			tabData.accept(MoreMiningModItems.LUMINISCENT_CRYSTAL.get());
			tabData.accept(MoreMiningModItems.LITHIUM.get());
			tabData.accept(MoreMiningModItems.NOXIUM_INGOT.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			tabData.accept(MoreMiningModBlocks.DEEP_SEA_VEIN.get().asItem());
			tabData.accept(MoreMiningModBlocks.INFERNAL_IRON_ORE.get().asItem());
			tabData.accept(MoreMiningModBlocks.ETHER_VEIN.get().asItem());
			tabData.accept(MoreMiningModBlocks.LUMINISCENCE_VEIN.get().asItem());
			tabData.accept(MoreMiningModBlocks.LITHIUM_ORE.get().asItem());
			tabData.accept(MoreMiningModBlocks.COMPRESSED_STONE.get().asItem());
			tabData.accept(MoreMiningModBlocks.NOXIUM_ORE.get().asItem());
			tabData.accept(MoreMiningModBlocks.COMPRESSED_STONE_IRON_ORE.get().asItem());
			tabData.accept(MoreMiningModBlocks.COMPRESSED_STONE_REDSTONE_ORE.get().asItem());
			tabData.accept(MoreMiningModBlocks.COMPRESSED_STONE_GOLD_ORE.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(MoreMiningModItems.DEEPER_LAYER.get());
			tabData.accept(MoreMiningModItems.LITHIUM_DRILL.get());
			tabData.accept(MoreMiningModItems.SECOND_LAYER.get());
			tabData.accept(MoreMiningModItems.NOXIUM_PICKAXE.get());
			tabData.accept(MoreMiningModItems.TEDDY_BEAR.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(MoreMiningModItems.DEEP_SKELETON_SPAWN_EGG.get());
			tabData.accept(MoreMiningModItems.DEAD_MINER_SPAWN_EGG.get());
			tabData.accept(MoreMiningModItems.STONE_GOLEM_SPAWN_EGG.get());
			tabData.accept(MoreMiningModItems.SEEKER_SPAWN_EGG.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
			tabData.accept(MoreMiningModItems.NIGHT_VISION_GLASSES_HELMET.get());
			tabData.accept(MoreMiningModItems.NOXIUM_ARMOR_HELMET.get());
			tabData.accept(MoreMiningModItems.NOXIUM_ARMOR_CHESTPLATE.get());
			tabData.accept(MoreMiningModItems.NOXIUM_ARMOR_LEGGINGS.get());
			tabData.accept(MoreMiningModItems.NOXIUM_ARMOR_BOOTS.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
			tabData.accept(MoreMiningModItems.LITHIUM_BATTERY.get());
		}
	}
}
