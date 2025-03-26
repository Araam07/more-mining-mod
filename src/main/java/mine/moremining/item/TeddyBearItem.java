package mine.moremining.item;

import mine.moremining.procedures.SecondLayerLowVisionProcedure;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.ChatFormatting;

import java.util.List;

public class TeddyBearItem extends Item {
	public TeddyBearItem() {
		super(new Item.Properties()
				.stacksTo(3) // Máximo 3 en un stack
				.rarity(Rarity.UNCOMMON)
		);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (!level.isClientSide()) {
			// Verificar cooldown
			if (player.getCooldowns().isOnCooldown(this)) {
				player.sendSystemMessage(Component.literal("¡Necesitas esperar para volver a abrazar al osito!")
						.withStyle(ChatFormatting.RED));
				return InteractionResultHolder.pass(stack);
			}

			// Restaurar sanidad
			SecondLayerLowVisionProcedure.restoreMentalHealth(20.0f);
			player.playSound(SoundEvents.ITEM_PICKUP, 1.0f, 1.2f);

			stack.shrink(1); // Consumir 1 unidad

			// Mensaje de feedback
			player.sendSystemMessage(Component.literal("¡El osito te dio ánimos! (+20% Sanidad)")
					.withStyle(ChatFormatting.LIGHT_PURPLE));

			// Cooldown de 30 segundos (600 ticks)
			player.getCooldowns().addCooldown(this, 600);
		}

		return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
	}

	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, level, tooltip, flag);

		tooltip.add(Component.literal("Consume 1 unidad por uso")
				.withStyle(ChatFormatting.GRAY));

		tooltip.add(Component.literal("30 segundos de cooldown")
				.withStyle(ChatFormatting.DARK_GRAY));

		tooltip.add(Component.literal("Restaura 20% de sanidad mental")
				.withStyle(ChatFormatting.BLUE));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BLOCK; // Animación de "abrazo"
	}
}