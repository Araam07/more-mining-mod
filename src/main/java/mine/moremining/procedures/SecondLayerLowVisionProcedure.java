package mine.moremining.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;

import mine.moremining.init.MoreMiningModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class SecondLayerLowVisionProcedure {
    // Cambiado a float para valores decimales
    private static float mentalHealth = 100.0f; // Salud mental inicial (100%)
    private static int mentalHealthCooldown = 0; // Cooldown para reducir la salud mental

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            execute(event, event.player.level(), event.player);
        }
    }

    public static void execute(LevelAccessor world, Entity entity) {
        execute(null, world, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
        if (entity == null)
            return;

        // Verificar si el jugador está en la segunda capa
        if ((entity.level().dimension()) == ResourceKey.create(Registries.DIMENSION, new ResourceLocation("more_mining:second_layer"))) {
            // Lógica de visión baja (tu código original)
            if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == MoreMiningModItems.NIGHT_VISION_GLASSES_HELMET.get()) {
                if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.BLINDNESS) ? _livEnt.getEffect(MobEffects.BLINDNESS).getDuration() : 0) < 800) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                        _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 1000, 1, false, false));
                }
                if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.NIGHT_VISION) ? _livEnt.getEffect(MobEffects.NIGHT_VISION).getDuration() : 0) < 800) {
                    {
                        ItemStack _ist = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY);
                        if (_ist.hurt(20, RandomSource.create(), null)) {
                            _ist.shrink(1);
                            _ist.setDamageValue(0);
                        }
                    }
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                        _entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1000, 2, false, false));
                }
            } else {
                if (entity instanceof LivingEntity _entity)
                    _entity.removeEffect(MobEffects.NIGHT_VISION);
                if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.BLINDNESS) ? _livEnt.getEffect(MobEffects.BLINDNESS).getDuration() : 0) < 800) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                        _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 1000, 3, false, false));
                }
            }

            // Actualizar la salud mental (solo para jugadores)
            if (entity instanceof Player) {
                updateMentalHealth((Player) entity);
            }
        }
    }

    private static void updateMentalHealth(Player player) {
        if (mentalHealthCooldown <= 0) {
            mentalHealth -= 0.5f; // Reduce la salud mental en 0.5% cada vez (ajusta este valor según lo rápido que quieras que disminuya)
            mentalHealthCooldown = 40; // Cooldown de 1 segundo (20 ticks)
        } else {
            mentalHealthCooldown--;
        }

        // Asegurarse de que no sea negativo
        mentalHealth = Math.max(mentalHealth, 0.0f);

        // Aplicar efectos según el nivel de salud mental
        if (mentalHealth <= 20.0f) { // Crítico (≤ 20%)
            applyCriticalMentalEffects(player);
        } else if (mentalHealth <= 50.0f) { // Bajo (≤ 50%)
            applyLowMentalEffects(player);
        } else if (mentalHealth <= 80.0f) { // Moderado (≤ 80%)
            applyModerateMentalEffects(player);
        }
    }

    private static void applyModerateMentalEffects(Player player) {
        // Efectos moderados: minería más lenta y sonidos aleatorios
        if (!player.level().isClientSide()) {
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 0)); // Minería más lenta
            if (player.getRandom().nextFloat() < 0.05f) { // 5% de probabilidad por tick
                player.playSound(SoundEvents.AMBIENT_CAVE.value(), 0.5f, 1.0f); // Sonidos de cueva
            }
        }
    }

    private static void applyLowMentalEffects(Player player) {
        // Efectos bajos: tirar objetos al azar y visión borrosa
        if (player.level().getGameTime() % 100 == 0) { // Cada 5 segundos
            ItemStack randomItem = player.getInventory().getItem(player.getRandom().nextInt(player.getInventory().getContainerSize()));
            if (!randomItem.isEmpty()) {
                player.drop(randomItem, true);
            }
        }
        if (!player.level().isClientSide()) {
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0)); // Visión borrosa (náuseas)
        }
    }

    private static void applyCriticalMentalEffects(Player player) {
        // Efectos críticos: quitar armadura, sonidos de mobs y daño psicológico
        if (player.level().getGameTime() % 200 == 0) { // Cada 10 segundos
            // Quitar una pieza de armadura al azar
            EquipmentSlot slot = EquipmentSlot.values()[player.getRandom().nextInt(4)];
            ItemStack armor = player.getItemBySlot(slot);
            if (!armor.isEmpty()) {
                player.drop(armor, true);
                player.setItemSlot(slot, ItemStack.EMPTY);
            }

            // Sonidos de mobs hostiles
            player.playSound(SoundEvents.ENDERMAN_SCREAM, 1.0f, 1.0f);
        }

        // Daño psicológico (daño real si la sanidad llega a 0)
        if (mentalHealth <= 0.0f && player.level().getGameTime() % 100 == 0) {
            player.hurt(player.damageSources().magic(), 1.0f); // 1 corazón de daño cada 5 segundos
        }
    }

    // Método para restaurar salud mental (ahora acepta float)
    public static void restoreMentalHealth(float amount) {
        mentalHealth = Math.min(mentalHealth + amount, 100.0f); // Máximo 100%
    }

    // Método para obtener la salud mental (devuelve float)
    public static float getMentalHealth() {
        return mentalHealth;
    }
}