package mine.moremining.item.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import mine.moremining.item.LithiumDrillItem;

public class LithiumDrillItemModel extends GeoModel<LithiumDrillItem> {
	@Override
	public ResourceLocation getAnimationResource(LithiumDrillItem animatable) {
		return new ResourceLocation("more_mining", "animations/lithium_drill.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(LithiumDrillItem animatable) {
		return new ResourceLocation("more_mining", "geo/lithium_drill.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(LithiumDrillItem animatable) {
		return new ResourceLocation("more_mining", "textures/item/drill_texture_16.png");
	}
}
