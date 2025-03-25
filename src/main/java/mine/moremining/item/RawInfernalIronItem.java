
package mine.moremining.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class RawInfernalIronItem extends Item {
	public RawInfernalIronItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.UNCOMMON));
	}
}
