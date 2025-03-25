package mine.moremining.world.gen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class CustomChunkGenerator extends NoiseBasedChunkGenerator {
    public CustomChunkGenerator(BiomeSource biomeSource, Holder<NoiseGeneratorSettings> settings) {
        super(biomeSource, settings);
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess chunk) {
        ChunkPos chunkPos = chunk.getPos();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        TerrainNoiseGenerator noiseGenerator = new TerrainNoiseGenerator(chunkPos.x + chunkPos.z);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                double perlinValue = noiseGenerator.getPerlinValue(x + chunkPos.getMinBlockX(), z + chunkPos.getMinBlockZ());
                double simplexValue = noiseGenerator.getSimplexValue(x + chunkPos.getMinBlockX(), z + chunkPos.getMinBlockZ());

                // Altura base del terreno
                int baseHeight = (int) (64 + perlinValue * 16);

                // Generar el suelo
                for (int y = 0; y < baseHeight; y++) {
                    pos.set(x + chunkPos.getMinBlockX(), y, z + chunkPos.getMinBlockZ());
                    chunk.setBlockState(pos, Blocks.STONE.defaultBlockState(), false);
                }

                // Generar formaciones rocosas usando ruido Simplex
                if (simplexValue > 0.5) {
                    for (int y = baseHeight - 5; y < baseHeight; y++) {
                        pos.set(x + chunkPos.getMinBlockX(), y, z + chunkPos.getMinBlockZ());
                        chunk.setBlockState(pos, Blocks.COBBLESTONE.defaultBlockState(), false);
                    }
                }
            }
        }

        // Devolver el chunk generado como un CompletableFuture
        return CompletableFuture.completedFuture(chunk);
    }
}