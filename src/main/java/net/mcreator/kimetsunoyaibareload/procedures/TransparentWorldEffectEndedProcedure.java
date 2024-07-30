package net.mcreator.kimetsunoyaibareload.procedures;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.kimetsunoyaibareload.potion.TransparentWorldCooldownPotionEffect;
import net.mcreator.kimetsunoyaibareload.KnyrMod;

import java.util.Random;
import java.util.Map;

public class TransparentWorldEffectEndedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				KnyrMod.LOGGER.warn("Failed to load dependency entity for procedure TransparentWorldEffectEnded!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity)
					.addPotionEffect(new EffectInstance(TransparentWorldCooldownPotionEffect.potion, (int) 3600, (int) 1, (false), (false)));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(
					new EffectInstance(Effects.BLINDNESS, (int) (MathHelper.nextDouble(new Random(), 20, 200)), (int) 1, (false), (false)));
		entity.attackEntityFrom(DamageSource.MAGIC, (float) 50);
	}
}
