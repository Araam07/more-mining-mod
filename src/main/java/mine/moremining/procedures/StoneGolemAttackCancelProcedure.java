package mine.moremining.procedures;

import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import mine.moremining.entity.StoneGolemEntity;

public class StoneGolemAttackCancelProcedure {

    @SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof StoneGolemEntity) {
            StoneGolemEntity golem = (StoneGolemEntity) event.getSource().getEntity();

            // Solo cancelar el daño si el Golem está en estado de ataque
            if (golem.getPersistentData().getString("State").equals("Attacking")) {
                event.setCanceled(true);
            }
        }
    }
}