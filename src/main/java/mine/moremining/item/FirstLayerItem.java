package mine.moremining.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import mine.moremining.procedures.FirstLayerCanMakePortalProcedure;
import mine.moremining.block.FirstLayerPortalBlock;

public class FirstLayerItem extends Item {
	public FirstLayerItem() {
		super(new Item.Properties().rarity(Rarity.COMMON).durability(64));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player entity = context.getPlayer();
		BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
		ItemStack itemstack = context.getItemInHand();
		Level world = context.getLevel();

		// Obtener la dimensión actual del jugador
		ResourceKey<Level> dimension = world.dimension();

		if (!entity.mayUseItemAt(pos, context.getClickedFace(), itemstack)) {
			return InteractionResult.FAIL;
		} else {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			boolean success = false;

			// Pasar la dimensión al procedimiento
			if (world.isEmptyBlock(pos) && FirstLayerCanMakePortalProcedure.execute(world, dimension, entity)) {
				FirstLayerPortalBlock.portalSpawn(world, pos);
				itemstack.hurtAndBreak(1, entity, c -> c.broadcastBreakEvent(context.getHand()));
				success = true;
			}

			return success ? InteractionResult.SUCCESS : InteractionResult.FAIL;
		}
	}
}