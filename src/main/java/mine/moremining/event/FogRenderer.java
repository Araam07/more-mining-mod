package mine.moremining.event;

import mine.moremining.effect.LowVisionEffect;
import mine.moremining.MoreMiningMod;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.mojang.blaze3d.systems.RenderSystem; // Usa RenderSystem en lugar de GL11

@Mod.EventBusSubscriber(modid = "more_mining", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FogRenderer {

    @SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent event) {
        // Asegúrate de que estamos en la fase correcta del renderizado
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                // Verifica si el jugador tiene el efecto LowVisionEffect
                MobEffectInstance effect = player.getEffect(MoreMiningMod.LOW_VISION_EFFECT);
                if (effect != null) {
                    int amplifier = effect.getAmplifier(); // Obtiene el nivel del efecto
                    float fogDensity = calculateFogDensity(amplifier); // Calcula la densidad de la niebla

                    // Usa RenderSystem para manipular la niebla de manera segura
                    RenderSystem.setShaderFogStart(0.0F); // Inicio de la niebla
                    RenderSystem.setShaderFogEnd(fogDensity); // Fin de la niebla (ajusta la densidad)
                } else {
                    // Restablece la niebla a su valor predeterminado
                    RenderSystem.setShaderFogStart(0.0F);
                    RenderSystem.setShaderFogEnd(1.0F); // Valor predeterminado
                }
            }
        }
    }

    private static float calculateFogDensity(int amplifier) {
        // Ajusta la densidad de la niebla en función del nivel del efecto
        return 5.0F + (amplifier * 3.0F); // Ejemplo: 5 + (nivel * 3)
    }
}