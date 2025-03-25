package mine.moremining.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class MineEmperorPickaxeScreen extends Screen {
    private final ItemStack pickaxeStack;

    // Constructor simplificado
    public MineEmperorPickaxeScreen() {
        super(Component.literal("Upgrade Pickaxe"));
        this.pickaxeStack = ItemStack.EMPTY; // Puedes cambiar esto si necesitas un ItemStack específico
    }

    @Override
    protected void init() {
        // Botón para mejorar la velocidad de minería
        this.addRenderableWidget(Button.builder(Component.literal("Upgrade Mining Speed"), button -> {
            // Lógica para mejorar la velocidad de minería
        }).bounds(this.width / 2 - 50, this.height / 2 - 30, 100, 20).build());

        // Botón para mejorar la durabilidad
        this.addRenderableWidget(Button.builder(Component.literal("Upgrade Durability"), button -> {
            // Lógica para mejorar la durabilidad
        }).bounds(this.width / 2 - 50, this.height / 2, 100, 20).build());

        // Botón para mejorar la fortuna
        this.addRenderableWidget(Button.builder(Component.literal("Upgrade Fortune"), button -> {
            // Lógica para mejorar la fortuna
        }).bounds(this.width / 2 - 50, this.height / 2 + 30, 100, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        // Renderizar el fondo
        this.renderBackground(guiGraphics);

        // Renderizar los componentes de la GUI
        super.render(guiGraphics, mouseX, mouseY, partialTicks);

        // Mostrar los puntos de mejora disponibles
        int upgradePoints = pickaxeStack.getOrCreateTag().getInt("UpgradePoints");
        guiGraphics.drawString(this.font, "Upgrade Points: " + upgradePoints, this.width / 2 - 50, this.height / 2 - 60, 0xFFFFFF);
    }

    @Override
    public boolean isPauseScreen() {
        return false; // La GUI no pausa el juego
    }
}