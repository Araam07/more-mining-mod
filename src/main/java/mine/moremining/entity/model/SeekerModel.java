package mine.moremining.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import mine.moremining.entity.SeekerEntity;

public class SeekerModel extends GeoModel<SeekerEntity> {
	@Override
	public ResourceLocation getAnimationResource(SeekerEntity entity) {
		return new ResourceLocation("more_mining", "animations/seeker.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(SeekerEntity entity) {
		return new ResourceLocation("more_mining", "geo/seeker.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(SeekerEntity entity) {
		return new ResourceLocation("more_mining", "textures/entities/" + entity.getTexture() + ".png");
	}

}
