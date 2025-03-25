package mine.moremining.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MineEmperorPickaxe extends Item {
	// Constants for configuration
	private static final float BASE_MINING_SPEED = 1.0F; // Base mining speed of the pickaxe
	private static final float UPGRADE_FACTOR = 0.05F; // Speed increase per toolLevel
	private static final int XP_PER_LEVEL = 100; // XP points required to level up
	private static final int UPGRADE_POINTS_PER_LEVEL = 1; // Upgrade points per level

	public MineEmperorPickaxe(Properties properties) {
		super(properties);
	}

	// ==================================================
	// Methods related to experience and toolLevel
	// ==================================================

	// Get the XP points of the pickaxe
	public static int getXPPoints(ItemStack stack) {
		CompoundTag nbt = stack.getOrCreateTag();
		return nbt.getInt("XPPoints");
	}

	// Set the XP points of the pickaxe
	public static void setXPPoints(ItemStack stack, int points) {
		CompoundTag nbt = stack.getOrCreateTag();
		nbt.putInt("XPPoints", points);
	}

	// Add XP points to the pickaxe
	public static void addXPPoints(ItemStack stack, int points) {
		int currentPoints = getXPPoints(stack);
		setXPPoints(stack, currentPoints + points);

		// Check if the pickaxe levels up
		checkLevelUp(stack);
	}

	// Get the toolLevel of the pickaxe
	public static int getToolLevel(ItemStack stack) {
		CompoundTag nbt = stack.getOrCreateTag();
		return nbt.getInt("ToolLevel");
	}

	// Set the toolLevel of the pickaxe
	public static void setToolLevel(ItemStack stack, int toolLevel) {
		CompoundTag nbt = stack.getOrCreateTag();
		nbt.putInt("ToolLevel", toolLevel);
	}

	// Level up the pickaxe
	public static void levelUp(ItemStack stack) {
		int currentToolLevel = getToolLevel(stack);
		setToolLevel(stack, currentToolLevel + 1);

		// Add upgrade points
		addUpgradePoints(stack, UPGRADE_POINTS_PER_LEVEL);

		// Reset XP points after leveling up
		setXPPoints(stack, 0);
	}

	// Check if the pickaxe levels up
	public static void checkLevelUp(ItemStack stack) {
		int xpPoints = getXPPoints(stack);
		if (xpPoints >= XP_PER_LEVEL) {
			levelUp(stack);
		}
	}

	// Get the XP required per level
	public static int getXPPerLevel() {
		return XP_PER_LEVEL;
	}

	// ==================================================
	// Methods related to upgrade points
	// ==================================================

	// Get the upgrade points of the pickaxe
	public static int getUpgradePoints(ItemStack stack) {
		CompoundTag nbt = stack.getOrCreateTag();
		return nbt.getInt("UpgradePoints");
	}

	// Set the upgrade points of the pickaxe
	public static void setUpgradePoints(ItemStack stack, int points) {
		CompoundTag nbt = stack.getOrCreateTag();
		nbt.putInt("UpgradePoints", points);
	}

	// Add upgrade points to the pickaxe
	public static void addUpgradePoints(ItemStack stack, int points) {
		int currentPoints = getUpgradePoints(stack);
		setUpgradePoints(stack, currentPoints + points);
	}

	// ==================================================
	// Methods related to mining speed
	// ==================================================

	// Calculate the final mining speed
	public static float getFinalMiningSpeed(ItemStack stack) {
		int toolLevel = getToolLevel(stack);
		return BASE_MINING_SPEED + (toolLevel * UPGRADE_FACTOR);
	}

	// ==================================================
	// Item behavior methods
	// ==================================================

	// Override the mining speed
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		// Check if the block is mineable with a pickaxe
		if (state.is(BlockTags.MINEABLE_WITH_PICKAXE)) {
			// Return the final mining speed
			return getFinalMiningSpeed(stack);
		}
		// If not mineable with a pickaxe, return the base speed
		return super.getDestroySpeed(stack, state);
	}

	// Override to check if the tool is correct for drops
	@Override
	public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
		// Check if the block is mineable with a pickaxe
		return state.is(BlockTags.MINEABLE_WITH_PICKAXE);
	}

	// Display information in the tooltip
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, level, tooltip, flag);

		// Display the toolLevel
		int toolLevel = getToolLevel(stack);
		tooltip.add(Component.literal("§7Tool Level: " + toolLevel));

		// Display the XP points
		int xpPoints = getXPPoints(stack);
		tooltip.add(Component.literal("§7XP: " + xpPoints + "/" + getXPPerLevel()));

		// Display the upgrade points
		int upgradePoints = getUpgradePoints(stack);
		tooltip.add(Component.literal("§7Upgrade Points: " + upgradePoints));

		// Display the mining speed
		float miningSpeed = getFinalMiningSpeed(stack);
		tooltip.add(Component.literal("§7Mining Speed: " + miningSpeed));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
	}

	@Mod.EventBusSubscriber
	private static class MineEmperorPickaxeForgeBusEvents {
		@SubscribeEvent
		public static void serverLoad(ServerStartingEvent event) {
		}

		@OnlyIn(Dist.CLIENT)
		@SubscribeEvent
		public static void clientLoad(FMLClientSetupEvent event) {
		}
	}
}