
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package mine.moremining.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import mine.moremining.block.SpectrumiteOreBlock;
import mine.moremining.block.SecondLayerPortalBlock;
import mine.moremining.block.NoxiumOreBlock;
import mine.moremining.block.LuminiscenceVeinBlock;
import mine.moremining.block.LithiumOreBlock;
import mine.moremining.block.InfernalIronOreBlock;
import mine.moremining.block.FirstLayerPortalBlock;
import mine.moremining.block.EtherVeinBlock;
import mine.moremining.block.DeepSeaVeinBlock;
import mine.moremining.block.CompressedStoneRedstoneOreBlock;
import mine.moremining.block.CompressedStoneIronOreBlock;
import mine.moremining.block.CompressedStoneGoldOreBlock;
import mine.moremining.block.CompressedStoneDiamondOreBlock;
import mine.moremining.block.CompressedStoneCoalOreBlock;
import mine.moremining.block.CompressedStoneBlock;
import mine.moremining.MoreMiningMod;

public class MoreMiningModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, MoreMiningMod.MODID);
	public static final RegistryObject<Block> DEEP_SEA_VEIN = REGISTRY.register("deep_sea_vein", () -> new DeepSeaVeinBlock());
	public static final RegistryObject<Block> INFERNAL_IRON_ORE = REGISTRY.register("infernal_iron_ore", () -> new InfernalIronOreBlock());
	public static final RegistryObject<Block> ETHER_VEIN = REGISTRY.register("ether_vein", () -> new EtherVeinBlock());
	public static final RegistryObject<Block> DEEPER_LAYER_PORTAL = REGISTRY.register("deeper_layer_portal", () -> new FirstLayerPortalBlock());
	public static final RegistryObject<Block> LUMINISCENCE_VEIN = REGISTRY.register("luminiscence_vein", () -> new LuminiscenceVeinBlock());
	public static final RegistryObject<Block> LITHIUM_ORE = REGISTRY.register("lithium_ore", () -> new LithiumOreBlock());
	public static final RegistryObject<Block> COMPRESSED_STONE = REGISTRY.register("compressed_stone", () -> new CompressedStoneBlock());
	public static final RegistryObject<Block> SECOND_LAYER_PORTAL = REGISTRY.register("second_layer_portal", () -> new SecondLayerPortalBlock());
	public static final RegistryObject<Block> NOXIUM_ORE = REGISTRY.register("noxium_ore", () -> new NoxiumOreBlock());
	public static final RegistryObject<Block> SPECTRUMITE_ORE = REGISTRY.register("spectrumite_ore", () -> new SpectrumiteOreBlock());
	public static final RegistryObject<Block> COMPRESSED_STONE_DIAMOND_ORE = REGISTRY.register("compressed_stone_diamond_ore", () -> new CompressedStoneDiamondOreBlock());
	public static final RegistryObject<Block> COMPRESSED_STONE_IRON_ORE = REGISTRY.register("compressed_stone_iron_ore", () -> new CompressedStoneIronOreBlock());
	public static final RegistryObject<Block> COMPRESSED_STONE_REDSTONE_ORE = REGISTRY.register("compressed_stone_redstone_ore", () -> new CompressedStoneRedstoneOreBlock());
	public static final RegistryObject<Block> COMPRESSED_STONE_GOLD_ORE = REGISTRY.register("compressed_stone_gold_ore", () -> new CompressedStoneGoldOreBlock());
	public static final RegistryObject<Block> COMPRESSED_STONE_COAL_ORE = REGISTRY.register("compressed_stone_coal_ore", () -> new CompressedStoneCoalOreBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
