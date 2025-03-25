
package mine.moremining.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class NoxiumIngotItem extends Item {
	public NoxiumIngotItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON));
	}
}
