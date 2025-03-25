package mine.moremining.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.entity.Entity;

import mine.moremining.network.MoreMiningModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class LithiumDrillRightclickedProcedure {
	@SubscribeEvent
	public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
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
