
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package mine.moremining.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

import mine.moremining.client.renderer.StoneGolemRenderer;
import mine.moremining.client.renderer.SeekerRenderer;
import mine.moremining.client.renderer.DeepSkeletonRenderer;
import mine.moremining.client.renderer.DeadMinerRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MoreMiningModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MoreMiningModEntities.DEEP_SKELETON.get(), DeepSkeletonRenderer::new);
		event.registerEntityRenderer(MoreMiningModEntities.DEEP_SKELETON_PROJECTILE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(MoreMiningModEntities.DEAD_MINER.get(), DeadMinerRenderer::new);
		event.registerEntityRenderer(MoreMiningModEntities.STONE_GOLEM.get(), StoneGolemRenderer::new);
		event.registerEntityRenderer(MoreMiningModEntities.THROWABLE_PICKAXE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(MoreMiningModEntities.SEEKER.get(), SeekerRenderer::new);
	}
}
