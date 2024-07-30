package net.mcreator.kimetsunoyaibareload.world;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import net.minecraft.world.GameRules;

import net.mcreator.kimetsunoyaibareload.KnyrModElements;

import java.lang.reflect.Method;

@KnyrModElements.ModElement.Tag
public class KnyrSaveOniLevelGameRule extends KnyrModElements.ModElement {
	public static final GameRules.RuleKey<GameRules.BooleanValue> gamerule = GameRules.register("knyrSaveOniLevel", GameRules.Category.MISC,
			create(true));

	public KnyrSaveOniLevelGameRule(KnyrModElements instance) {
		super(instance, 16);
	}

	public static GameRules.RuleType<GameRules.BooleanValue> create(boolean defaultValue) {
		try {
			Method createGameruleMethod = ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "func_223568_b", boolean.class);
			createGameruleMethod.setAccessible(true);
			return (GameRules.RuleType<GameRules.BooleanValue>) createGameruleMethod.invoke(null, defaultValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
