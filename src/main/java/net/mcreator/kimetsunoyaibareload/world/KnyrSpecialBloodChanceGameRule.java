package net.mcreator.kimetsunoyaibareload.world;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import net.minecraft.world.GameRules;

import net.mcreator.kimetsunoyaibareload.KnyrModElements;

import java.lang.reflect.Method;

@KnyrModElements.ModElement.Tag
public class KnyrSpecialBloodChanceGameRule extends KnyrModElements.ModElement {
	public static final GameRules.RuleKey<GameRules.IntegerValue> gamerule = GameRules.register("knyrSpecialBloodChance", GameRules.Category.MISC,
			create(100));

	public KnyrSpecialBloodChanceGameRule(KnyrModElements instance) {
		super(instance, 20);
	}

	public static GameRules.RuleType<GameRules.IntegerValue> create(int defaultValue) {
		try {
			Method createGameruleMethod = ObfuscationReflectionHelper.findMethod(GameRules.IntegerValue.class, "func_223559_b", int.class);
			createGameruleMethod.setAccessible(true);
			return (GameRules.RuleType<GameRules.IntegerValue>) createGameruleMethod.invoke(null, defaultValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
