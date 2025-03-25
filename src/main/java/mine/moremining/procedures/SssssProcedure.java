package mine.moremining.procedures;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import mine.moremining.network.MoreMiningModVariables;
import mine.moremining.MoreMiningMod;

public class SssssProcedure {
	public static void execute(LevelAccessor world, double x, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(MoreMiningModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MoreMiningModVariables.PlayerVariables())).firstLoad == true) {
			MoreMiningMod.queueServerWork(1, () -> {
				if (world instanceof ServerLevel _serverworld) {
					StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("more_mining", "mine_castle"));
					if (template != null) {
						template.placeInWorld(_serverworld, new BlockPos(0, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) x, (int) z), 0),
								new BlockPos(0, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) x, (int) z), 0), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false),
								_serverworld.random, 3);
					}
				}
			});
			{
				boolean _setval = false;
				entity.getCapability(MoreMiningModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.firstLoad = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity.getCapability(MoreMiningModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MoreMiningModVariables.PlayerVariables())).firstLoad == false) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("This is not your first time"), false);
		}
	}
}
