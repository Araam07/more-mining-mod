package mine.moremining.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import java.util.Comparator;
import java.util.List;

public class StoneGolemOnEntityTickUpdateProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) return;

        // Verificar si el Golem no está en estado "Attacking" o "Cooldown"
        if (!entity.getPersistentData().getString("State").equals("Attacking") && 
            !entity.getPersistentData().getString("State").equals("Cooldown")) {
            
            // Verificar si el Golem tiene un objetivo
            Entity target = (entity instanceof Mob _mobEnt) ? _mobEnt.getTarget() : null;
            if (target != null) {
                // Buscar entidades cercanas en un radio alrededor del mob
                final Vec3 _center = new Vec3(x, y, z);
                List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(10 / 2d), e -> true) // Radio de 5 bloques
                        .stream()
                        .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
                        .toList();

                // Verificar si el objetivo está cerca
                for (Entity entityiterator : _entfound) {
                    if (entityiterator == target) {
                        entity.getPersistentData().putString("State", "Attacking"); // Cambiar a estado "Attacking"
                        entity.getPersistentData().putDouble("AttackTimer", 0); // Reiniciar el contador de ataque
                        break; // Salir del bucle si se encuentra el objetivo
                    }
                }
            }
        }

        // Si el Golem está en estado "Attacking", ejecutar la lógica de ataque
        if (entity.getPersistentData().getString("State").equals("Attacking")) {
            StoneGolemAttackProcedure.execute(world, x, y, z, entity);
        }
    }
}