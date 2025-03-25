
/*
*    MCreator note: This file will be REGENERATED on each build.
*/
package mine.moremining.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import mine.moremining.item.TeddyBearItem;
import mine.moremining.item.SpectrumiteIngotItem;
import mine.moremining.item.SecondLayerItem;
import mine.moremining.item.RawSpectrumiteItem;
import mine.moremining.item.RawNoxiumItem;
import mine.moremining.item.RawInfernalIronItem;
import mine.moremining.item.NoxiumPickaxeItem;
import mine.moremining.item.NoxiumIngotItem;
import mine.moremining.item.NoxiumArmorItem;
import mine.moremining.item.NightVisionGlassesItem;
import mine.moremining.item.MineEmperorPickaxe;
import mine.moremining.item.LuminiscentCrystalItem;
import mine.moremining.item.LithiumItem;
import mine.moremining.item.LithiumDrillItem;
import mine.moremining.item.LithiumBatteryItem;
import mine.moremining.item.InfernalIronItem;
import mine.moremining.item.FirstLayerItem;
import mine.moremining.item.EtherShardItem;
import mine.moremining.item.DeepSeaCrystalItem;
import mine.moremining.MoreMiningMod;

public class MoreMiningModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MoreMiningMod.MODID);
	public static final RegistryObject<Item> DEEP_SEA_CRYSTAL = REGISTRY.register("deep_sea_crystal", () -> new DeepSeaCrystalItem());
	public static final RegistryObject<Item> INFERNAL_IRON_INGOT = REGISTRY.register("infernal_iron_ingot", () -> new InfernalIronItem());
	public static final RegistryObject<Item> ETHER_SHARD = REGISTRY.register("ether_shard", () -> new EtherShardItem());
	public static final RegistryObject<Item> DEEP_SEA_VEIN = block(MoreMiningModBlocks.DEEP_SEA_VEIN);
	public static final RegistryObject<Item> INFERNAL_IRON_ORE = block(MoreMiningModBlocks.INFERNAL_IRON_ORE);
	public static final RegistryObject<Item> RAW_INFERNAL_IRON = REGISTRY.register("raw_infernal_iron", () -> new RawInfernalIronItem());
	public static final RegistryObject<Item> ETHER_VEIN = block(MoreMiningModBlocks.ETHER_VEIN);
	public static final RegistryObject<Item> DEEPER_LAYER = REGISTRY.register("deeper_layer", () -> new FirstLayerItem());
	public static final RegistryObject<Item> DEEP_SKELETON_SPAWN_EGG = REGISTRY.register("deep_skeleton_spawn_egg", () -> new ForgeSpawnEggItem(MoreMiningModEntities.DEEP_SKELETON, -10066330, -13421773, new Item.Properties()));
	public static final RegistryObject<Item> DEAD_MINER_SPAWN_EGG = REGISTRY.register("dead_miner_spawn_egg", () -> new ForgeSpawnEggItem(MoreMiningModEntities.DEAD_MINER, -13421773, -6710887, new Item.Properties()));
	public static final RegistryObject<Item> LUMINISCENT_CRYSTAL = REGISTRY.register("luminiscent_crystal", () -> new LuminiscentCrystalItem());
	public static final RegistryObject<Item> LUMINISCENCE_VEIN = block(MoreMiningModBlocks.LUMINISCENCE_VEIN);
	public static final RegistryObject<Item> NIGHT_VISION_GLASSES_HELMET = REGISTRY.register("night_vision_glasses_helmet", () -> new NightVisionGlassesItem.Helmet());
	public static final RegistryObject<Item> LITHIUM = REGISTRY.register("lithium", () -> new LithiumItem());
	public static final RegistryObject<Item> LITHIUM_ORE = block(MoreMiningModBlocks.LITHIUM_ORE);
	public static final RegistryObject<Item> LITHIUM_BATTERY = REGISTRY.register("lithium_battery", () -> new LithiumBatteryItem());
	public static final RegistryObject<Item> STONE_GOLEM_SPAWN_EGG = REGISTRY.register("stone_golem_spawn_egg", () -> new ForgeSpawnEggItem(MoreMiningModEntities.STONE_GOLEM, -13421773, -10066330, new Item.Properties()));
	public static final RegistryObject<Item> LITHIUM_DRILL = REGISTRY.register("lithium_drill", () -> new LithiumDrillItem());
	public static final RegistryObject<Item> COMPRESSED_STONE = block(MoreMiningModBlocks.COMPRESSED_STONE);
	public static final RegistryObject<Item> SECOND_LAYER = REGISTRY.register("second_layer", () -> new SecondLayerItem());
	public static final RegistryObject<Item> SEEKER_SPAWN_EGG = REGISTRY.register("seeker_spawn_egg", () -> new ForgeSpawnEggItem(MoreMiningModEntities.SEEKER, -10066330, -10092544, new Item.Properties()));
	public static final RegistryObject<Item> NOXIUM_INGOT = REGISTRY.register("noxium_ingot", () -> new NoxiumIngotItem());
	public static final RegistryObject<Item> NOXIUM_ORE = block(MoreMiningModBlocks.NOXIUM_ORE);
	public static final RegistryObject<Item> NOXIUM_ARMOR_HELMET = REGISTRY.register("noxium_armor_helmet", () -> new NoxiumArmorItem.Helmet());
	public static final RegistryObject<Item> NOXIUM_ARMOR_CHESTPLATE = REGISTRY.register("noxium_armor_chestplate", () -> new NoxiumArmorItem.Chestplate());
	public static final RegistryObject<Item> NOXIUM_ARMOR_LEGGINGS = REGISTRY.register("noxium_armor_leggings", () -> new NoxiumArmorItem.Leggings());
	public static final RegistryObject<Item> NOXIUM_ARMOR_BOOTS = REGISTRY.register("noxium_armor_boots", () -> new NoxiumArmorItem.Boots());
	public static final RegistryObject<Item> NOXIUM_PICKAXE = REGISTRY.register("noxium_pickaxe", () -> new NoxiumPickaxeItem());
	public static final RegistryObject<Item> RAW_NOXIUM = REGISTRY.register("raw_noxium", () -> new RawNoxiumItem());
	public static final RegistryObject<Item> SPECTRUMITE_INGOT = REGISTRY.register("spectrumite_ingot", () -> new SpectrumiteIngotItem());
	public static final RegistryObject<Item> RAW_SPECTRUMITE = REGISTRY.register("raw_spectrumite", () -> new RawSpectrumiteItem());
	public static final RegistryObject<Item> SPECTRUMITE_ORE = block(MoreMiningModBlocks.SPECTRUMITE_ORE);
	public static final RegistryObject<Item> COMPRESSED_STONE_DIAMOND_ORE = block(MoreMiningModBlocks.COMPRESSED_STONE_DIAMOND_ORE);
	public static final RegistryObject<Item> COMPRESSED_STONE_IRON_ORE = block(MoreMiningModBlocks.COMPRESSED_STONE_IRON_ORE);
	public static final RegistryObject<Item> COMPRESSED_STONE_REDSTONE_ORE = block(MoreMiningModBlocks.COMPRESSED_STONE_REDSTONE_ORE);
	public static final RegistryObject<Item> COMPRESSED_STONE_GOLD_ORE = block(MoreMiningModBlocks.COMPRESSED_STONE_GOLD_ORE);
	public static final RegistryObject<Item> COMPRESSED_STONE_COAL_ORE = block(MoreMiningModBlocks.COMPRESSED_STONE_COAL_ORE);
	public static final RegistryObject<Item> TEDDY_BEAR = REGISTRY.register("teddy_bear", () -> new TeddyBearItem());
	// Start of user code block custom items
	public static final RegistryObject<Item> MINE_EMPEROR_PICKAXE = REGISTRY.register("mine_emperor_pickaxe", () -> new MineEmperorPickaxe(new Item.Properties()));

	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
