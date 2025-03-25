
package mine.moremining.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.HumanoidModel;

import mine.moremining.entity.DeadMinerEntity;

public class DeadMinerRenderer extends HumanoidMobRenderer<DeadMinerEntity, HumanoidModel<DeadMinerEntity>> {
	public DeadMinerRenderer(EntityRendererProvider.Context context) {
		super(context, new HumanoidModel<DeadMinerEntity>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
		this.addLayer(new HumanoidArmorLayer(this, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
	}

	@Override
	public ResourceLocation getTextureLocation(DeadMinerEntity entity) {
		return new ResourceLocation("more_mining:textures/entities/deadminer.png");
	}
}
