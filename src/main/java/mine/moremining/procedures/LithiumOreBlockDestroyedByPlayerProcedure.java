package mine.moremining.procedures;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.server.level.ServerLevel;

import mine.moremining.init.MoreMiningModItems;

public class LithiumOreBlockDestroyedByPlayerProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if ((blockstate.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp1 && blockstate.getValue(_getbp1)) == true) {
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(MoreMiningModItems.LITHIUM.get()));
				entityToSpawn.setPickUpDelay(10);
				_level.addFreshEntity(entityToSpawn);
			}
			return true;
		}
		if (world instanceof Level _level && !_level.isClientSide())
			_level.explode(null, x, y, z, 2, Level.ExplosionInteraction.BLOCK);
		return false;
	}
}
