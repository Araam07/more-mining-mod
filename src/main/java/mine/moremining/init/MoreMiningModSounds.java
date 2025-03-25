
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package mine.moremining.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import mine.moremining.MoreMiningMod;

public class MoreMiningModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MoreMiningMod.MODID);
	public static final RegistryObject<SoundEvent> DEEP_CAVE = REGISTRY.register("deep-cave", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("more_mining", "deep-cave")));
	public static final RegistryObject<SoundEvent> CAVE_TAPS = REGISTRY.register("cave-taps", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("more_mining", "cave-taps")));
	public static final RegistryObject<SoundEvent> WATERDROPS_CAVE_ECHO = REGISTRY.register("waterdrops-cave-echo", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("more_mining", "waterdrops-cave-echo")));
	public static final RegistryObject<SoundEvent> OLD_MINE_AMBIENCE = REGISTRY.register("old-mine-ambience", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("more_mining", "old-mine-ambience")));
	public static final RegistryObject<SoundEvent> SINISTER_AMBIENT = REGISTRY.register("sinister-ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("more_mining", "sinister-ambient")));
	public static final RegistryObject<SoundEvent> SINISTER_PIANO = REGISTRY.register("sinister-piano", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("more_mining", "sinister-piano")));
	public static final RegistryObject<SoundEvent> SINISTER_SOUNDS = REGISTRY.register("sinister-sounds", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("more_mining", "sinister-sounds")));
	public static final RegistryObject<SoundEvent> STONE_THINGS = REGISTRY.register("stone-things", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("more_mining", "stone-things")));
	public static final RegistryObject<SoundEvent> SEEKER_STEP = REGISTRY.register("seeker-step", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("more_mining", "seeker-step")));
	public static final RegistryObject<SoundEvent> BOO_AND_LAUGH = REGISTRY.register("boo-and-laugh", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("more_mining", "boo-and-laugh")));
	public static final RegistryObject<SoundEvent> SEEKER_SOUNDS = REGISTRY.register("seeker-sounds", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("more_mining", "seeker-sounds")));
}
