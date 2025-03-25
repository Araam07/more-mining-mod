
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package mine.moremining.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import mine.moremining.client.gui.PickaxeUpgradeGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MoreMiningModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(MoreMiningModMenus.PICKAXE_UPGRADE_GUI.get(), PickaxeUpgradeGUIScreen::new);
		});
	}
}
