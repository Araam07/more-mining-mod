
package mine.moremining.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.HumanoidModel;

import mine.moremining.entity.DeepSkeletonEntity;

public class DeepSkeletonRenderer extends HumanoidMobRenderer<DeepSkeletonEntity, HumanoidModel<DeepSkeletonEntity>> {
	public DeepSkeletonRenderer(EntityRendererProvider.Context context) {
		super(context, new HumanoidModel<DeepSkeletonEntity>(context.bakeLayer(ModelLayers.PLAYER)), 0.6f);
		this.addLayer(new HumanoidArmorLayer(this, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
	}

	@Override
	public ResourceLocation getTextureLocation(DeepSkeletonEntity entity) {
		return new ResourceLocation("more_mining:textures/entities/deep_skeleton1.png");
	}
}
