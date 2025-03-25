
package mine.moremining.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.BlockPos;

import mine.moremining.procedures.NoxiumPickaxeSanityPenaltyProcedure;
import mine.moremining.init.MoreMiningModItems;

public class NoxiumPickaxeItem extends PickaxeItem {
	public NoxiumPickaxeItem() {
		super(new Tier() {
			public int getUses() {
				return 864;
			}

			public float getSpeed() {
				return 6.5f;
			}

			public float getAttackDamageBonus() {
				return 2f;
			}

			public int getLevel() {
				return 3;
			}

			public int getEnchantmentValue() {
				return 22;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(MoreMiningModItems.NOXIUM_INGOT.get()));
			}
		}, 1, -3f, new Item.Properties());
	}

	@Override
	public boolean mineBlock(ItemStack itemstack, Level world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
		boolean retval = super.mineBlock(itemstack, world, blockstate, pos, entity);
		NoxiumPickaxeSanityPenaltyProcedure.execute(world, entity);
		return retval;
	}
}
