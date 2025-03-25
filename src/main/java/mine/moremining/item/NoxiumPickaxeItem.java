
package mine.moremining.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

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
}
