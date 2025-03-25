package mine.moremining.procedures;

import net.minecraft.world.entity.Entity;

import mine.moremining.network.MoreMiningModVariables;

public class LithiumDrillRightclickedOnBlockProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			boolean _setval = true;
			entity.getCapability(MoreMiningModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.rightClick = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
