package mine.moremining.world.gen;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

import java.util.List;

public class TerrainNoiseGenerator {
    private final PerlinSimplexNoise perlinNoise;
    private final SimplexNoise simplexNoise;

    public TerrainNoiseGenerator(long seed) {
        RandomSource random = RandomSource.create(seed);
        this.perlinNoise = new PerlinSimplexNoise(random, List.of(0));
        this.simplexNoise = new SimplexNoise(random);
    }

    public double getPerlinValue(int x, int z) {
        return perlinNoise.getValue(x * 0.05, z * 0.05, false);
    }

    public double getSimplexValue(int x, int z) {
        return simplexNoise.getValue(x * 0.1, z * 0.1);
    }
}