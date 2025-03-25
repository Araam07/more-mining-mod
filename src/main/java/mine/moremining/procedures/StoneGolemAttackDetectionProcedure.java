package mine.moremining.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import java.util.Comparator;
import java.util.List;

public class StoneGolemAttackDetectionProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) return;

        // Verificar si el Golem tiene un objetivo
        Entity target = (entity instanceof Mob _mobEnt) ? _mobEnt.getTarget() : null;
        if (target != null) {
            // Detectar si el objetivo está dentro del rango de ataque (5 bloques de radio)
            final Vec3 _center = new Vec3(x, y + 1.75, z); // Centrar la detección en la altura del Golem
            List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(5.0), e -> true)
                    .stream()
                    .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
                    .toList();

            for (Entity entityiterator : _entfound) {
                if (entityiterator == target) {
                    entity.getPersistentData().putString("State", "Attacking"); // Cambiar a estado "Attacking"
                    break; // Salir del bucle si se encuentra el objetivo
                }
            }
        }

        // Si el Golem está en estado de ataque, ejecutar el ataque
        if (entity.getPersistentData().getString("State").equals("Attacking")) {
            StoneGolemAttackProcedure.execute(world, x, y, z, entity);
        }
    }
}