package mine.moremining.item;

import mine.moremining.procedures.SecondLayerLowVisionProcedure;
import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;

import java.util.List;

public class TeddyBearItem extends Item {
	public TeddyBearItem() {
		super(new Item.Properties()
				.stacksTo(1)
				.rarity(Rarity.COMMON)
				.durability(10)); // Opcional: añade durabilidad si quieres que se gaste
	}

	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, level, tooltip, flag);
		tooltip.add(Component.translatable("item.more_mining.teddy_bear.description_0")
				.withStyle(ChatFormatting.GRAY));
		tooltip.add(Component.translatable("Restaura 20% de sanidad mental al usar")
				.withStyle(ChatFormatting.BLUE));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);

		if (!level.isClientSide()) {
			// Restaurar sanidad
			SecondLayerLowVisionProcedure.restoreMentalHealth(20.0f);

			// Efectos de feedback
			player.playSound(SoundEvents.ITEM_PICKUP, 1.0f, 1.2f);
			player.sendSystemMessage(Component.literal("¡El osito de peluche te hace sentir mejor!")
					.withStyle(ChatFormatting.LIGHT_PURPLE));

			// Consumir un uso (opcional)
			itemstack.hurtAndBreak(1, player,
					entity -> entity.broadcastBreakEvent(hand));
		}

		return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BLOCK; // Animación de abrazo
	}
}