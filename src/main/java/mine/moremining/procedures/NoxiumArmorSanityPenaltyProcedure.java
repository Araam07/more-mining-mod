package mine.moremining.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.sounds.SoundEvents;

import mine.moremining.init.MoreMiningModItems;

@Mod.EventBusSubscriber
public class NoxiumArmorSanityPenaltyProcedure {

	private static final float SANITY_COST_PER_ARMOR_PIECE = 0.1f; // Penalización por cada pieza

	@SubscribeEvent
	public static void onPlayerHurt(LivingHurtEvent event) {
		if (!(event.getEntity() instanceof Player player)) return;

		int noxiumArmorCount = 0;

		// Contar cuántas piezas de armadura de Noxium lleva equipadas
		for (ItemStack armor : player.getArmorSlots()) {
			if (armor.getItem() == MoreMiningModItems.NOXIUM_ARMOR_HELMET.get() ||
					armor.getItem() == MoreMiningModItems.NOXIUM_ARMOR_CHESTPLATE.get() ||
					armor.getItem() == MoreMiningModItems.NOXIUM_ARMOR_LEGGINGS.get() ||
					armor.getItem() == MoreMiningModItems.NOXIUM_ARMOR_BOOTS.get()) {
				noxiumArmorCount++;
			}
		}

		if (noxiumArmorCount > 0) {
			// Aplicar penalización basada en la cantidad de piezas
			float totalSanityLoss = SANITY_COST_PER_ARMOR_PIECE * noxiumArmorCount;
			SecondLayerLowVisionProcedure.restoreMentalHealth(-totalSanityLoss);

			// Efecto de sonido
			player.playSound(SoundEvents.SOUL_ESCAPE, 0.8f, 0.8f);
		}
	}

	// Execute para compatibilidad con MCreator
	public static void execute(LevelAccessor world, Entity entity) {
		// Puede dejarse vacío o usarse para debug
	}
}