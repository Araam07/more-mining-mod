package mine.moremining.init;

import mine.moremining.MoreMiningMod;
import mine.moremining.menu.MineEmperorPickaxeMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.ItemStack; // Importación añadida
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MoreMiningMenus {
    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MoreMiningMod.MODID);

    public static final RegistryObject<MenuType<MineEmperorPickaxeMenu>> MINE_EMPEROR_PICKAXE_MENU = REGISTRY.register(
            "mine_emperor_pickaxe_menu",
            () -> new MenuType<>((containerId, playerInventory) -> new MineEmperorPickaxeMenu(containerId, playerInventory, ItemStack.EMPTY), FeatureFlags.DEFAULT_FLAGS)
    );
}