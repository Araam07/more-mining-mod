package mine.moremining.procedures;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import mine.moremining.network.MoreMiningModVariables;
import mine.moremining.item.LithiumDrillItem;
import mine.moremining.MoreMiningMod;

public class LithiumDrillItemInHandTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double ParX = 0;
		double ParY = 0;
		double ParZ = 0;
		if (true == (entity.getCapability(MoreMiningModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MoreMiningModVariables.PlayerVariables())).rightClick) {
			if (itemstack.getItem() instanceof LithiumDrillItem)
				itemstack.getOrCreateTag().putString("geckoAnim", "animation.drill.digging");
			MoreMiningMod.queueServerWork((int) (3 * world.getBlockState(BlockPos.containing(x, y, z)).getDestroySpeed(world, BlockPos.containing(x, y, z))), () -> {
				for (int index0 = 0; index0 < 5; index0++) {
					world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId((world.getBlockState(BlockPos.containing(x, y, z)))));
				}
				if (true == (entity.getCapability(MoreMiningModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MoreMiningModVariables.PlayerVariables())).rightClick) {
					world.destroyBlock(BlockPos.containing(x, y, z), false);
				}
			});
			{
				boolean _setval = false;
				entity.getCapability(MoreMiningModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.rightClick = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if (false == (entity.getCapability(MoreMiningModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MoreMiningModVariables.PlayerVariables())).rightClick) {
			if (itemstack.getItem() instanceof LithiumDrillItem)
				itemstack.getOrCreateTag().putString("geckoAnim", "animation.drill.idle");
		}
	}
}
