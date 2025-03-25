
package mine.moremining.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class InfernalIronOreBlock extends Block {
	public InfernalIronOreBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.NETHERRACK).strength(2f, 10f).lightLevel(s -> 2));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}
}
