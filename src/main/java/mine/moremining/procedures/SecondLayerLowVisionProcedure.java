package mine.moremining.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

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

    // Configuración
    private static final float DIMENSION_DECAY = 0.5f;  // Pérdida en segunda capa
    private static final float ARMOR_PENALTY = 1.5f;    // Sanidad perdida por daño con armadura

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
        } else if (mentalHealth <= 50.0f) {
            applyLowSanityEffects(player);
        } else if (mentalHealth <= 80.0f) {
            applyModerateEffects(player);
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
            player.playSound(SoundEvents.ENDERMAN_SCREAM, 1.0f, 1.0f);
        }

        if (mentalHealth <= 0.0f && player.level().getGameTime() % 100 == 0) {
            player.hurt(player.damageSources().magic(), 1.0f);
        }
    }

    /* === Utilidades === */
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
}