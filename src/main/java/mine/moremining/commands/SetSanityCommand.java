package mine.moremining.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import mine.moremining.procedures.SecondLayerLowVisionProcedure;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class SetSanityCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("setsanity")
                        .requires(source -> source.hasPermission(2)) // Solo para operadores
                        .then(Commands.argument("valor", FloatArgumentType.floatArg(0, 100))
                                .executes(context -> {
                                    float value = FloatArgumentType.getFloat(context, "valor");
                                    SecondLayerLowVisionProcedure.restoreMentalHealth(value - SecondLayerLowVisionProcedure.getMentalHealth());
                                    context.getSource().sendSuccess(
                                            () -> Component.literal("§aSanidad mental establecida a: §e" + value + "%"),
                                            true
                                    );
                                    return Command.SINGLE_SUCCESS;
                                })
                        ));
    }
}