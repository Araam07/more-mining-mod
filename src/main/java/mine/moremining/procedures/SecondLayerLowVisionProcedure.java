package mine.moremining.procedures;

import mine.moremining.MoreMiningMod;
import mine.moremining.entity.SeekerEntity;
import mine.moremining.init.MoreMiningModEntities;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import java.util.Arrays;
import java.util.List;

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

    // Sistema de sanidad
    private static float mentalHealth = 100.0f;
    private static int mentalHealthCooldown = 0;

    //Arrays de sonidos
    private static final List<SoundEvent> LOW_SANITY_SOUNDS = Arrays.asList(
            SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD.get(),
            SoundEvents.AMBIENT_BASALT_DELTAS_MOOD.get(),
            SoundEvents.SOUL_ESCAPE,
            SoundEvents.ENDERMAN_SCREAM,
            SoundEvents.AMBIENT_CAVE.get()
    );

    private static final List<SoundEvent> CRITICAL_SANITY_SOUNDS = Arrays.asList(
            SoundEvents.WARDEN_NEARBY_CLOSE,
            SoundEvents.WARDEN_HEARTBEAT,
            SoundEvents.SCULK_SHRIEKER_SHRIEK,
            SoundEvents.ELDER_GUARDIAN_CURSE
    );

    private static SoundEvent resolveSoundEvent(Object sound) {
        return sound instanceof SoundEvent ? (SoundEvent) sound : ((Holder<SoundEvent>) sound).value();
    }

    private static void playRandomSound(Player player, List<SoundEvent> sounds, float volume, float pitchVariation, float chance) {
        if (!player.level().isClientSide() && player.level().getRandom().nextFloat() < chance) {
            SoundEvent sound = sounds.get(player.level().getRandom().nextInt(sounds.size()));
            float variedPitch = pitchVariation + (player.level().getRandom().nextFloat() * 0.4f - 0.2f);

            // Sonidos "falsos" que parecen venir de diferentes direcciones
            double xOffset = player.level().getRandom().nextDouble() * 10 - 5;
            double zOffset = player.level().getRandom().nextDouble() * 10 - 5;

            player.level().playSound(
                    null,
                    player.getX() + xOffset,
                    player.getY(),
                    player.getZ() + zOffset,
                    sound,
                    SoundSource.AMBIENT,
                    volume,
                    variedPitch
            );
        }
    }

    // Configuración
    private static final float DIMENSION_DECAY = 0.5f;  // Pérdida en segunda capa
    private static final float ARMOR_PENALTY = 1.0f;    // Sanidad perdida por daño con armadura

    /* === Eventos === */
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            execute(event, event.player.level(), event.player);
        }
    }

    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            checkNoxiumArmor(player);
        }
    }

    /* === Lógica Principal === */
    public static void execute(LevelAccessor world, Entity entity) {
        execute(null, world, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
        if (entity == null || !(entity instanceof Player player)) return;

        if (isInSecondLayer(player)) {
            handleDimensionEffects(player);
            handleDimensionDecay(player);
            handleSeekerSpawning(player);
        }

        applyGlobalSanityEffects(player);
    }

    /* === Funciones Específicas === */
    private static void handleDimensionEffects(Player player) {
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        boolean hasSpecialHelmet = helmet.getItem() == MoreMiningModItems.NIGHT_VISION_GLASSES_HELMET.get();

        if (hasSpecialHelmet) {
            if (needsBlindness(player)) {
                player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 1000, 1, false, false));
            }
            if (needsNightVision(player)) {
                helmet.hurtAndBreak(20, player, (e) -> e.broadcastBreakEvent(EquipmentSlot.HEAD));
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1000, 2, false, false));
            }
        } else {
            player.removeEffect(MobEffects.NIGHT_VISION);
            if (needsBlindness(player)) {
                player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 1000, 3, false, false));
            }
        }
    }

    private static void handleDimensionDecay(Player player) {
        if (mentalHealthCooldown <= 0) {
            mentalHealth -= DIMENSION_DECAY;
            mentalHealthCooldown = 40;
        } else {
            mentalHealthCooldown--;
        }
        mentalHealth = Math.max(mentalHealth, 0.0f);
    }

    private static void checkNoxiumArmor(Player player) {
        int pieces = 0;
        for (ItemStack armor : player.getArmorSlots()) {
            if (armor.getItem() == MoreMiningModItems.NOXIUM_ARMOR_HELMET.get() ||
                    armor.getItem() == MoreMiningModItems.NOXIUM_ARMOR_CHESTPLATE.get() ||
                    armor.getItem() == MoreMiningModItems.NOXIUM_ARMOR_LEGGINGS.get() ||
                    armor.getItem() == MoreMiningModItems.NOXIUM_ARMOR_BOOTS.get()) {
                pieces++;
            }
        }

        if (pieces > 0) {
            restoreMentalHealth(-ARMOR_PENALTY * pieces);
            player.playSound(SoundEvents.SOUL_ESCAPE, 0.7f, 0.8f);
        }
    }

    /* === Efectos Globales === */
    private static void applyGlobalSanityEffects(Player player) {
        if (mentalHealth <= 20.0f) {
            applyCriticalEffects(player);
            playRandomSound(player, CRITICAL_SANITY_SOUNDS, 0.6f, 0.8f, 0.1f);
        } else if (mentalHealth <= 50.0f) {
            applyLowSanityEffects(player);
            playRandomSound(player, LOW_SANITY_SOUNDS, 0.4f, 1.2f, 0.05f);
        } else if (mentalHealth <= 80.0f) {
            applyModerateEffects(player);
            if (player.level().getRandom().nextFloat() < 0.03f) {
                player.playSound(SoundEvents.AMBIENT_UNDERWATER_EXIT, 0.5f, 0.8f);
            }
        }
    }

    private static void applyModerateEffects(Player player) {
        if (!player.level().isClientSide()) {
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 0));
            if (player.getRandom().nextFloat() < 0.05f) {
                player.playSound(SoundEvents.AMBIENT_CAVE.value(), 0.5f, 1.0f);
            }
        }
    }

    private static void applyLowSanityEffects(Player player) {
        if (player.level().getGameTime() % 100 == 0) {
            ItemStack randomItem = player.getInventory().getItem(player.getRandom().nextInt(player.getInventory().getContainerSize()));
            if (!randomItem.isEmpty()) {
                player.drop(randomItem, true);
            }
        }
        if (!player.level().isClientSide()) {
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
        }
    }

    private static void applyCriticalEffects(Player player) {
        if (player.level().getGameTime() % 200 == 0) {
            EquipmentSlot slot = EquipmentSlot.values()[player.getRandom().nextInt(4)];
            ItemStack armor = player.getItemBySlot(slot);
            if (!armor.isEmpty()) {
                player.drop(armor, true);
                player.setItemSlot(slot, ItemStack.EMPTY);
            }

            // Sonido aleatorio en lugar de fijo
            playRandomSound(player, CRITICAL_SANITY_SOUNDS, 1.0f, 0.8f, 1.0f);
        }

        if (mentalHealth <= 0.0f && player.level().getGameTime() % 100 == 0) {
            player.hurt(player.damageSources().magic(), 1.0f);
            // Susurro siniestro al recibir daño psicológico
            player.playSound(SoundEvents.EVOKER_PREPARE_SUMMON, 0.8f, 0.5f);
        }
    }

    /* === Utilidades === */

    private static Vec3 calculateOrbitPosition(Player player, double radius) {
        float angle = player.level().random.nextFloat() * 360;
        return new Vec3(
                player.getX() + Mth.cos(angle * Mth.DEG_TO_RAD) * radius,
                player.getY() + 1.5,
                player.getZ() + Mth.sin(angle * Mth.DEG_TO_RAD) * radius
        );
    }

    private static boolean isInSecondLayer(Entity entity) {
        return entity.level().dimension() ==
                ResourceKey.create(Registries.DIMENSION,
                        new ResourceLocation("more_mining:second_layer"));
    }

    private static boolean needsBlindness(LivingEntity entity) {
        return entity.getEffect(MobEffects.BLINDNESS) == null ||
                entity.getEffect(MobEffects.BLINDNESS).getDuration() < 800;
    }

    private static boolean needsNightVision(LivingEntity entity) {
        return entity.getEffect(MobEffects.NIGHT_VISION) == null ||
                entity.getEffect(MobEffects.NIGHT_VISION).getDuration() < 800;
    }

    public static void restoreMentalHealth(float amount) {
        mentalHealth = Math.min(Math.max(mentalHealth + amount, 0.0f), 100.0f);
    }

    public static float getMentalHealth() {
        return mentalHealth;
    }

    //Seeker Management
    private static void handleSeekerSpawning(Player player) {
        if (!isInSecondLayer(player) || player.level().isClientSide()) return;

        // Verificar si hay Seekers cerca (32 bloques)
        boolean seekerNearby = !player.level().getEntitiesOfClass(SeekerEntity.class,
                player.getBoundingBox().inflate(32)).isEmpty();

        // Si no hay Seekers cerca y la sanidad es baja, hay chance de spawnear uno
        if (!seekerNearby && getMentalHealth() < 50 && player.level().getRandom().nextFloat() < 0.01f) {
            Vec3 spawnPos = calculateOrbitPosition(player, 16 + player.level().getRandom().nextDouble() * 8);
            SeekerEntity seeker = MoreMiningModEntities.SEEKER.get().create(player.level());
            if (seeker != null) {
                seeker.moveTo(spawnPos.x, spawnPos.y, spawnPos.z,
                        player.level().getRandom().nextFloat() * 360, 0);
                player.level().addFreshEntity(seeker);
                seeker.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 0.8F);
            }
        }
    }
}