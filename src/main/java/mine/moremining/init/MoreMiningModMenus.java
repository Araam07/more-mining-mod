
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package mine.moremining.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import mine.moremining.world.inventory.PickaxeUpgradeGUIMenu;
import mine.moremining.MoreMiningMod;

public class MoreMiningModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MoreMiningMod.MODID);
	public static final RegistryObject<MenuType<PickaxeUpgradeGUIMenu>> PICKAXE_UPGRADE_GUI = REGISTRY.register("pickaxe_upgrade_gui", () -> IForgeMenuType.create(PickaxeUpgradeGUIMenu::new));
}
