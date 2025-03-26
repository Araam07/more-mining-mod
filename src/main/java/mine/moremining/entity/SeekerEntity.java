package mine.moremining.entity;

import net.minecraft.sounds.SoundEvents;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;

import mine.moremining.init.MoreMiningModEntities;

public class SeekerEntity extends Monster implements GeoEntity {
    public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(SeekerEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(SeekerEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(SeekerEntity.class, EntityDataSerializers.STRING);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean swinging;
    private boolean lastloop;
    private long lastSwing;
    public String animationprocedure = "empty";

    public SeekerEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(MoreMiningModEntities.SEEKER.get(), world);
    }

    public SeekerEntity(EntityType<SeekerEntity> type, Level world) {
        super(type, world);
        xpReward = 0;
        setNoAi(false);
        setMaxUpStep(0.6f);
        setPersistenceRequired();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SHOOT, false);
        this.entityData.define(ANIMATION, "undefined");
        this.entityData.define(TEXTURE, "seeker");
    }

    public void setTexture(String texture) {
        this.entityData.set(TEXTURE, texture);
    }

    public String getTexture() {
        return this.entityData.get(TEXTURE);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        // Goal personalizado para el comportamiento del Seeker
        this.goalSelector.addGoal(1, new Goal() {
            private Player targetPlayer;
            private int cooldown = 0;
            private boolean isScaring = false;
            private boolean isInRange = false;

            @Override
            public boolean canUse() {
                // Busca un jugador cercano como objetivo
                this.targetPlayer = SeekerEntity.this.level().getNearestPlayer(SeekerEntity.this, 16);
                return this.targetPlayer != null;
            }

            @Override
            public void start() {
                // Inicia el comportamiento
                this.cooldown = 0;
                this.isScaring = false;
                this.isInRange = false;
            }

            @Override
            public void stop() {
                // Detiene el comportamiento
                this.targetPlayer = null;
                this.isScaring = false;
                this.isInRange = false;
            }

            @Override
            public void tick() {
                if (this.targetPlayer != null) {
                    double distance = SeekerEntity.this.distanceTo(this.targetPlayer);

                    if (!this.isScaring && this.cooldown <= 0) {
                        if (distance > 6) {
                            // Si está demasiado lejos, acercarse
                            SeekerEntity.this.getNavigation().moveTo(this.targetPlayer, 1.0);
                            this.isInRange = false;
                        } else if (distance > 3) {
                            // Si está dentro del rango de "acecho", quedarse quieto
                            if (!this.isInRange) {
                                SeekerEntity.this.getNavigation().stop(); // Detener el movimiento
                                this.isInRange = true;
                            }

                            // Si el jugador mira al Seeker, iniciar la animación de asustar
                            if (isPlayerLookingAtSeeker()) {
                                this.isScaring = true;
                                SeekerEntity.this.setAnimation("animation.seeker.scare");
                                this.cooldown = 55; // Duración de la animación de asustar
                            }
                        } else {
                            // Si está a menos de 3 bloques, iniciar la animación de asustar
                            this.isScaring = true;
                            SeekerEntity.this.setAnimation("animation.seeker.scare");
                            this.cooldown = 55; // Duración de la animación de asustar
                        }
                    } else if (this.isScaring) {
                        // Fase 2: Asustar al jugador
                        if (this.cooldown > 0) {
                            this.cooldown--;

                            // Cuando la animación de asustar está en su punto máximo, infligir daño
                            if (this.cooldown == 42) { // 55 - 13 = 42 (el daño ocurre a los 13 ticks)
                                this.targetPlayer.hurt(SeekerEntity.this.damageSources().mobAttack(SeekerEntity.this), 4.0F);
                                // Reproducir el sonido de ataque
                                SeekerEntity.this.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("more_mining:boo-and-laugh")), 1.0F, 1.0F);
                            }
                        } else {
                            // Después de asustar, huir y reiniciar el comportamiento
                            this.isScaring = false;
                            Vec3 fleePos = SeekerEntity.this.position().add((Math.random() - 0.5) * 32, 0, (Math.random() - 0.5) * 32); // Alejarse más lejos
                            SeekerEntity.this.getNavigation().moveTo(fleePos.x, fleePos.y, fleePos.z, 2.0); // Huir más rápido
                            this.cooldown = 100; // Cooldown después de huir (100 ticks = 5 segundos)
                        }
                    } else if (this.cooldown > 0) {
                        // Cooldown activo: no hacer nada
                        this.cooldown--;
                    }
                }
            }

            private boolean isPlayerLookingAtSeeker() {
                if (this.targetPlayer != null) {
                    Vec3 playerLookVec = this.targetPlayer.getLookAngle();
                    Vec3 seekerToPlayerVec = SeekerEntity.this.position().subtract(this.targetPlayer.position()).normalize();
                    double dotProduct = playerLookVec.dot(seekerToPlayerVec);
                    return dotProduct > 0.99; // El jugador está mirando directamente al Seeker
                }
                return false;
            }

            @Override
            public boolean requiresUpdateEveryTick() {
                return true; // Asegura que el Goal se actualice cada tick
            }
        });
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("more_mining:seeker-sounds"));
    }

    @Override
    public void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("more_mining:seeker-step")), 0.15f, 1);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("Texture", this.getTexture());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Texture"))
            this.setTexture(compound.getString("Texture"));
    }

    @Override
    public void baseTick() {
        super.baseTick();
        this.refreshDimensions();
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        // Ajusta las dimensiones de la hitbox
        return EntityDimensions.scalable(1.4F, 3.0F); // Ancho: 1.0 bloques, Alto: 2.0 bloques
    }

    @Override
    public float getEyeHeight(Pose pose) {
        // Ajusta la altura de los ojos para que coincida con la hitbox
        return 2.6F; // Altura de los ojos en bloques
    }

    // Nueva clase interna para el comportamiento de persecución
    public static class SeekerChaseGoal extends Goal {
        private final SeekerEntity seeker;
        private Player targetPlayer;
        private int cooldown = 0;
        private boolean isScaring = false;
        private int respawnTimer = 0;
        private Vec3 lastKnownPosition;

        public SeekerChaseGoal(SeekerEntity seeker) {
            this.seeker = seeker;
        }

        @Override
        public boolean canUse() {
            this.targetPlayer = this.seeker.level().getNearestPlayer(this.seeker, 32);
            return this.targetPlayer != null;
        }

        @Override
        public void start() {
            this.cooldown = 0;
            this.isScaring = false;
            this.respawnTimer = 0;
            this.lastKnownPosition = this.targetPlayer.position();
        }

        @Override
        public void stop() {
            this.targetPlayer = null;
            this.isScaring = false;
            this.respawnTimer = 0;
        }

        @Override
        public void tick() {
            if (this.targetPlayer == null) return;

            double distance = this.seeker.distanceTo(this.targetPlayer);
            this.lastKnownPosition = this.targetPlayer.position();

            // Si el jugador se aleja demasiado, preparar respawn
            if (distance > 48 && !this.isScaring) {
                handlePlayerTooFar();
                return;
            }

            // Comportamiento normal de persecución
            if (!this.isScaring && this.cooldown <= 0) {
                if (distance > 6) {
                    this.seeker.getNavigation().moveTo(this.targetPlayer, 1.2); // Mayor velocidad de persecución
                } else if (distance > 3) {
                    this.seeker.getNavigation().stop();
                    if (isPlayerLookingAtSeeker()) {
                        startScaring();
                    }
                } else {
                    startScaring();
                }
            } else if (this.isScaring) {
                handleScaringPhase();
            } else if (this.cooldown > 0) {
                this.cooldown--;
            }
        }

        private void handlePlayerTooFar() {
            this.respawnTimer++;

            // Después de 5 segundos (100 ticks) sin ver al jugador, teletransportarse cerca
            if (this.respawnTimer >= 100) {
                Vec3 respawnPos = calculateRespawnPosition();
                this.seeker.teleportTo(respawnPos.x, respawnPos.y, respawnPos.z);
                this.respawnTimer = 0;
                this.seeker.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }
        }

        private Vec3 calculateRespawnPosition() {
            // Calcular posición 16-24 bloques del jugador en dirección aleatoria
            double angle = this.seeker.level().random.nextDouble() * Math.PI * 2;
            double distance = 16 + this.seeker.level().random.nextDouble() * 8;

            return new Vec3(
                    this.lastKnownPosition.x + Math.cos(angle) * distance,
                    this.lastKnownPosition.y,
                    this.lastKnownPosition.z + Math.sin(angle) * distance
            );
        }

        private void startScaring() {
            this.isScaring = true;
            this.seeker.setAnimation("animation.seeker.scare");
            this.cooldown = 55;
        }

        private void handleScaringPhase() {
            if (this.cooldown > 0) {
                this.cooldown--;
                if (this.cooldown == 42) {
                    this.targetPlayer.hurt(this.seeker.damageSources().mobAttack(this.seeker), 4.0F);
                    this.seeker.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("more_mining:boo-and-laugh")), 1.0F, 1.0F);
                }
            } else {
                this.isScaring = false;
                Vec3 fleePos = calculateFleePosition();
                this.seeker.getNavigation().moveTo(fleePos.x, fleePos.y, fleePos.z, 2.0);
                this.cooldown = 100;
            }
        }

        private Vec3 calculateFleePosition() {
            // Huir en dirección opuesta al jugador
            Vec3 awayDir = this.seeker.position().subtract(this.targetPlayer.position()).normalize();
            return this.seeker.position().add(awayDir.x * 16, 0, awayDir.z * 16);
        }

        private boolean isPlayerLookingAtSeeker() {
            if (this.targetPlayer == null) return false;
            Vec3 playerLookVec = this.targetPlayer.getLookAngle();
            Vec3 seekerToPlayerVec = this.seeker.position().subtract(this.targetPlayer.position()).normalize();
            return playerLookVec.dot(seekerToPlayerVec) > 0.99;
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }

    public static void init() {
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 10);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64);
        return builder;
    }

    private PlayState movementPredicate(AnimationState event) {
        if (this.animationprocedure.equals("empty")) {
            if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))

            ) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("animation.seeker.walk"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation.seeker.idle"));
        }
        return PlayState.STOP;
    }

    private PlayState attackingPredicate(AnimationState event) {
        double d1 = this.getX() - this.xOld;
        double d0 = this.getZ() - this.zOld;
        float velocity = (float) Math.sqrt(d1 * d1 + d0 * d0);
        if (getAttackAnim(event.getPartialTick()) > 0f && !this.swinging) {
            this.swinging = true;
            this.lastSwing = level().getGameTime();
        }
        if (this.swinging && this.lastSwing + 55L <= level().getGameTime()) {
            this.swinging = false;
        }
        if (this.swinging && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
            event.getController().forceAnimationReset();
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.seeker.scare"));
        }
        return PlayState.CONTINUE;
    }

    String prevAnim = "empty";

    private PlayState procedurePredicate(AnimationState event) {
        if (!animationprocedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED || (!this.animationprocedure.equals(prevAnim) && !this.animationprocedure.equals("empty"))) {
            if (!this.animationprocedure.equals(prevAnim))
                event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
            if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
                this.animationprocedure = "empty";
                event.getController().forceAnimationReset();
            }
        } else if (animationprocedure.equals("empty")) {
            prevAnim = "empty";
            return PlayState.STOP;
        }
        prevAnim = this.animationprocedure;
        return PlayState.CONTINUE;
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 20) {
            this.remove(SeekerEntity.RemovalReason.KILLED);
            this.dropExperience();
        }
    }

    public String getSyncedAnimation() {
        return this.entityData.get(ANIMATION);
    }

    public void setAnimation(String animation) {
        this.entityData.set(ANIMATION, animation);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "movement", 10, this::movementPredicate));
        data.add(new AnimationController<>(this, "attacking", 10, this::attackingPredicate));
        data.add(new AnimationController<>(this, "procedure", 10, this::procedurePredicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}