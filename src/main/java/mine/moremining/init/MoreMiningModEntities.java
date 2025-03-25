
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package mine.moremining.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import mine.moremining.entity.ThrowablePickaxeEntity;
import mine.moremining.entity.StoneGolemEntity;
import mine.moremining.entity.SeekerEntity;
import mine.moremining.entity.DeepSkeletonEntityProjectile;
import mine.moremining.entity.DeepSkeletonEntity;
import mine.moremining.entity.DeadMinerEntity;
import mine.moremining.MoreMiningMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MoreMiningModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MoreMiningMod.MODID);
	public static final RegistryObject<EntityType<DeepSkeletonEntity>> DEEP_SKELETON = register("deep_skeleton",
			EntityType.Builder.<DeepSkeletonEntity>of(DeepSkeletonEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(DeepSkeletonEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<DeepSkeletonEntityProjectile>> DEEP_SKELETON_PROJECTILE = register("projectile_deep_skeleton", EntityType.Builder.<DeepSkeletonEntityProjectile>of(DeepSkeletonEntityProjectile::new, MobCategory.MISC)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(DeepSkeletonEntityProjectile::new).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<DeadMinerEntity>> DEAD_MINER = register("dead_miner",
			EntityType.Builder.<DeadMinerEntity>of(DeadMinerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(DeadMinerEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<StoneGolemEntity>> STONE_GOLEM = register("stone_golem",
			EntityType.Builder.<StoneGolemEntity>of(StoneGolemEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(StoneGolemEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<ThrowablePickaxeEntity>> THROWABLE_PICKAXE = register("throwable_pickaxe", EntityType.Builder.<ThrowablePickaxeEntity>of(ThrowablePickaxeEntity::new, MobCategory.MISC)
			.setCustomClientFactory(ThrowablePickaxeEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<SeekerEntity>> SEEKER = register("seeker",
			EntityType.Builder.<SeekerEntity>of(SeekerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SeekerEntity::new)

					.sized(0.6f, 1.8f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			DeepSkeletonEntity.init();
			DeadMinerEntity.init();
			StoneGolemEntity.init();
			SeekerEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(DEEP_SKELETON.get(), DeepSkeletonEntity.createAttributes().build());
		event.put(DEAD_MINER.get(), DeadMinerEntity.createAttributes().build());
		event.put(STONE_GOLEM.get(), StoneGolemEntity.createAttributes().build());
		event.put(SEEKER.get(), SeekerEntity.createAttributes().build());
	}
}
