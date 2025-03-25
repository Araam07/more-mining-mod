package mine.moremining.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import mine.moremining.procedures.SecondLayerLowVisionProcedure;
import net.minecraft.world.entity.player.Player;

public class MentalHealthCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("mentalhealth")
                .executes(MentalHealthCommand::execute));
    }

    private static int execute(CommandContext<CommandSourceStack> context) {
        // Obtener el jugador que ejecuta el comando
        CommandSourceStack source = context.getSource();
        if (source.getEntity() instanceof Player player) {
            // Obtener la salud mental actual
            float mentalHealth = SecondLayerLowVisionProcedure.getMentalHealth();

            // Mostrar el valor de la salud mental
            player.sendSystemMessage(Component.literal("Tu salud mental actual es: " + mentalHealth + "%"));
            return 1; // Éxito
        } else {
            source.sendFailure(Component.literal("Este comando solo puede ser ejecutado por un jugador."));
            return 0; // Fallo
        }
    }
}