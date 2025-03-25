
package mine.moremining.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class SpectrumiteIngotItem extends Item {
	public SpectrumiteIngotItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
