package net.mcreator.kimetsunoyaibareload.procedures;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.kimetsunoyaibareload.KnyrMod;

import java.util.Random;
import java.util.Map;

public class TransparentWorldPerTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				KnyrMod.LOGGER.warn("Failed to load dependency entity for procedure TransparentWorldPerTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity.getPersistentData().getDouble("knyrTransparentWorldDamageTick") >= MathHelper.nextDouble(new Random(), 50, 200)) {
			entity.getPersistentData().putDouble("knyrTransparentWorldDamageTick", 0);
			entity.attackEntityFrom(DamageSource.MAGIC, (float) 10);
		} else {
			entity.getPersistentData().putDouble("knyrTransparentWorldDamageTick",
					(entity.getPersistentData().getDouble("knyrTransparentWorldDamageTick") + 1));
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 2, (int) 1, (false), (false)));
		}
	}
}
