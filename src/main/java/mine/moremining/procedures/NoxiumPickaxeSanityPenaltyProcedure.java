package mine.moremining.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import mine.moremining.init.MoreMiningModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class NoxiumPickaxeSanityPenaltyProcedure {
	private static final float SANITY_COST_PER_BLOCK = 0.5f;

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;

		// Tu lógica original puede ir aquí si es necesaria
	}

	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		Player player = event.getPlayer();
		if (player == null) return;

		ItemStack heldItem = player.getMainHandItem();

		if (heldItem.getItem() == MoreMiningModItems.NOXIUM_PICKAXE.get()) {
			SecondLayerLowVisionProcedure.restoreMentalHealth(-SANITY_COST_PER_BLOCK);
			player.playSound(SoundEvents.SOUL_ESCAPE, 0.5f, 1.0f);

			// Opcional: Ejecutar el execute para compatibilidad
			execute(event, player.level(), player);
		}
	}
}