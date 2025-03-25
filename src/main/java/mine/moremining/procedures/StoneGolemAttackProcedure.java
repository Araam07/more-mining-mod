package mine.moremining.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.Vec3;

import mine.moremining.entity.StoneGolemEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StoneGolemAttackProcedure {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) return;

        // Obtener el contador de ataque
        double attackTimer = entity.getPersistentData().getDouble("AttackTimer");

        // Iniciar ataque si el contador está en 0
        if (attackTimer == 0) {
            LOGGER.debug("[DEBUG] Iniciando ataque...");
            if (entity instanceof StoneGolemEntity) {
                ((StoneGolemEntity) entity).setAnimation("attack"); // Activar la animación de ataque
            }

            // Desactivar el movimiento durante el ataque
            if (entity instanceof Mob) {
                ((Mob) entity).getNavigation().stop(); // Detener el movimiento
                ((Mob) entity).setNoAi(true); // Desactivar la IA temporalmente
                LOGGER.debug("[DEBUG] Movimiento y IA desactivados.");
            }
        }

        // Incrementar el contador de ataque
        entity.getPersistentData().putDouble("AttackTimer", attackTimer + 1);

        // Generar partículas durante la preparación del ataque
        if (attackTimer < 25) { // Antes de la explosión
            if (world instanceof ServerLevel _level) {
                _level.sendParticles(ParticleTypes.ENCHANTED_HIT, x, y + 1.75, z, 5, 0.15, 0.15, 0.15, 0);
            }
        }

        // Generar explosión en el tick 25
        if (attackTimer == 25) {
            LOGGER.debug("[DEBUG] Generando explosión...");
            if (!world.isClientSide()) { // Solo en el lado del servidor
                if (world instanceof Level) {
                    // Calcular la posición de la explosión (1.5 bloques frente al mob)
                    Vec3 lookAngle = entity.getLookAngle(); // Dirección en la que el mob está mirando
                    double explosionX = x + lookAngle.x * 1.5;
                    double explosionY = y + entity.getEyeHeight(); // Usar la altura de los ojos del mob
                    double explosionZ = z + lookAngle.z * 1.5;

                    // Generar la explosión sin daño a bloques
                    ((Level) world).explode(entity, explosionX, explosionY, explosionZ, 3.0F, Level.ExplosionInteraction.NONE);
                }
            }
        }

        // Después de la explosión, iniciar el tiempo de espera
        if (attackTimer >= 25 && attackTimer < 45) {
            // No hacer nada, solo esperar
        }

        // Reiniciar después de 45 ticks
        if (attackTimer >= 45) {
            LOGGER.debug("[DEBUG] Reiniciando ataque...");
            entity.getPersistentData().putDouble("AttackTimer", 0); // Reiniciar el contador de ataque
            entity.getPersistentData().putString("State", "Cooldown"); // Cambiar a estado "Cooldown"

            // Reactivar el movimiento y la IA
            if (entity instanceof Mob) {
                ((Mob) entity).setNoAi(false); // Reactivar la IA
                Entity target = ((Mob) entity).getTarget();
                if (target instanceof LivingEntity) { // Verificar que el objetivo sea un LivingEntity
                    ((Mob) entity).setTarget((LivingEntity) target); // Establecer el objetivo
                    ((Mob) entity).getNavigation().moveTo(target, 1.0); // Reiniciar el movimiento hacia el objetivo
                    LOGGER.debug("[DEBUG] Movimiento reactivado hacia: " + target.getName().getString());
                } else {
                    LOGGER.debug("[DEBUG] No hay objetivo válido para reactivar el movimiento.");
                }
            }
        }

        // Después de 20 ticks de cooldown, volver al estado "Idle"
        if (entity.getPersistentData().getString("State").equals("Cooldown")) {
            double cooldownTimer = entity.getPersistentData().getDouble("CooldownTimer");
            if (cooldownTimer >= 20) {
                entity.getPersistentData().putString("State", "Idle"); // Volver al estado "Idle"
                entity.getPersistentData().putDouble("CooldownTimer", 0); // Reiniciar el contador de cooldown
                LOGGER.debug("[DEBUG] Cooldown completado. Volviendo a estado Idle.");
            } else {
                entity.getPersistentData().putDouble("CooldownTimer", cooldownTimer + 1); // Incrementar el contador de cooldown
            }
        }
    }
}