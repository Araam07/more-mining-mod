package mine.moremining.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

public class FirstLayerCanMakePortalProcedure {
	public static boolean execute(LevelAccessor world, ResourceKey<Level> dimension, Entity entity) {
		if (dimension == null || entity == null)
			return false;
		if (Level.OVERWORLD == dimension) {
			if (entity.getY() < 0) {
				if (!world.isClientSide() && world.getServer() != null)
					world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Correct depth"), false);
				return true;
			}
			if (!world.isClientSide() && world.getServer() != null)
				world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Build the portal deeper"), false);
			return false;
		}
		if (ResourceKey.create(Registries.DIMENSION, new ResourceLocation("more_mining:deeper_layer")) == dimension) {
			if (entity.getY() > 120) {
				if (!world.isClientSide() && world.getServer() != null)
					world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Correct height"), false);
				return true;
			}
			if (!world.isClientSide() && world.getServer() != null)
				world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Build the portal higher"), false);
			return false;
		}
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Wrond Dimension"), false);
		return false;
	}
}
