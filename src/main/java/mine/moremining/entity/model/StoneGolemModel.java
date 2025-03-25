package mine.moremining.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import mine.moremining.entity.StoneGolemEntity;

public class StoneGolemModel extends GeoModel<StoneGolemEntity> {
	@Override
	public ResourceLocation getAnimationResource(StoneGolemEntity entity) {
		return new ResourceLocation("more_mining", "animations/stonegolem.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(StoneGolemEntity entity) {
		return new ResourceLocation("more_mining", "geo/stonegolem.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(StoneGolemEntity entity) {
		return new ResourceLocation("more_mining", "textures/entities/" + entity.getTexture() + ".png");
	}

}
