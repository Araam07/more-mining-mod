package mine.moremining.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import mine.moremining.client.gui.MineEmperorPickaxeScreen;
import net.minecraft.client.Minecraft;

public class MoreMiningCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("upgradepickaxe")
                        .executes(context -> {
                            // Abrir la GUI en el lado del cliente
                            if (context.getSource().getEntity() != null && context.getSource().getEntity().level().isClientSide) {
                                Minecraft.getInstance().setScreen(new MineEmperorPickaxeScreen());
                            }
                            return 1;
                        })
        );
    }
}