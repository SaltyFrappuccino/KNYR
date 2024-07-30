package net.mcreator.kimetsunoyaibareload.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.kimetsunoyaibareload.KnyrModVariables;
import net.mcreator.kimetsunoyaibareload.KnyrMod;

import java.util.Map;

public class FeenProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				KnyrMod.LOGGER.warn("Failed to load dependency entity for procedure Feen!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(("" + (entity
					.getCapability(KnyrModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KnyrModVariables.PlayerVariables())).oni_level)),
					(false));
		}
	}
}
