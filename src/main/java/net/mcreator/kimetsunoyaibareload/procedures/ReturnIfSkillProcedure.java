package net.mcreator.kimetsunoyaibareload.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.kimetsunoyaibareload.world.KnyrShowSkillUIGameRule;
import net.mcreator.kimetsunoyaibareload.KnyrMod;

import java.util.Map;

public class ReturnIfSkillProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				KnyrMod.LOGGER.warn("Failed to load dependency world for procedure ReturnIfSkill!");
			return false;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if (world.getWorldInfo().getGameRulesInstance().getBoolean(KnyrShowSkillUIGameRule.gamerule) == true) {
			return true;
		}
		return false;
	}
}
