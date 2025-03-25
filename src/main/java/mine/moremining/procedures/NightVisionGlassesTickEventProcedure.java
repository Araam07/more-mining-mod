package mine.moremining.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

public class NightVisionGlassesTickEventProcedure {
	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (!((entity.level().dimension()) == ResourceKey.create(Registries.DIMENSION, new ResourceLocation("more_mining:second_layer")))) {
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.NIGHT_VISION) ? _livEnt.getEffect(MobEffects.NIGHT_VISION).getDuration() : 0) < 1600) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 2000, 1, true, false));
				{
					ItemStack _ist = itemstack;
					if (_ist.hurt(10, RandomSource.create(), null)) {
						_ist.shrink(1);
						_ist.setDamageValue(0);
					}
				}
			}
		}
	}
}
