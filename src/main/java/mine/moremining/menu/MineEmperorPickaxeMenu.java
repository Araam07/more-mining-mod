package mine.moremining.menu;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import mine.moremining.init.MoreMiningMenus;

import javax.annotation.Nonnull;

public class MineEmperorPickaxeMenu extends AbstractContainerMenu {
    // Si no necesitas pickaxeStack, puedes eliminarlo
    private final ItemStack pickaxeStack;

    public MineEmperorPickaxeMenu(int containerId, Inventory playerInventory, ItemStack pickaxeStack) {
        super(MoreMiningMenus.MINE_EMPEROR_PICKAXE_MENU.get(), containerId);
        this.pickaxeStack = pickaxeStack;

        // Si necesitas usar playerInventory, añade slots aquí
        // Ejemplo: addPlayerSlots(playerInventory);
    }

    @Override
    public boolean stillValid(@Nonnull Player player) {
        return true; // Permitir que el menú esté siempre abierto
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull Player player, int index) {
        // Implementación básica de transferencia rápida de ítems (shift-clic)
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 36) {
                if (!this.moveItemStackTo(itemstack1, 36, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 36, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    // Método opcional para añadir slots del jugador
    private void addPlayerSlots(Inventory playerInventory) {
        // Añadir slots del inventario del jugador
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        // Añadir barra de acceso rápido
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }
}