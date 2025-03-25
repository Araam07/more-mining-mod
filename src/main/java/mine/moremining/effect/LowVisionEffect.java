package mine.moremining.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class LowVisionEffect extends MobEffect {
    public LowVisionEffect() {
        super(MobEffectCategory.HARMFUL, 0x000000); // Color negro (similar a la ceguera)
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // No necesitamos hacer nada aquí, ya que la manipulación de la niebla se hará en el evento de renderización.
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // Aplicar el efecto continuamente
    }
}