package mine.moremining.event;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.network.chat.Component;

import mine.moremining.item.MineEmperorPickaxe;

@Mod.EventBusSubscriber(modid = "more_mining", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MiningEventHandler {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        // Obtén el ítem que el jugador está usando
        ItemStack stack = event.getPlayer().getMainHandItem();

        // Verifica si el ítem es el pico mejorable
        if (stack.getItem() instanceof MineEmperorPickaxe) {
            // Añade puntos de experiencia al pico
            MineEmperorPickaxe.addXPPoints(stack, 10); // Añade 10 puntos de experiencia

            // Opcional: Muestra un mensaje en el chat con la información actual del pico
            int toolLevel = MineEmperorPickaxe.getToolLevel(stack);
            int xpPoints = MineEmperorPickaxe.getXPPoints(stack);
            event.getPlayer().sendSystemMessage(Component.literal("Tool Level: " + toolLevel + ", XP: " + xpPoints + "/" + MineEmperorPickaxe.getXPPerLevel()));
        }
    }
}