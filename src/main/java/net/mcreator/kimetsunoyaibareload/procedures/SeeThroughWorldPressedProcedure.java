package net.mcreator.kimetsunoyaibareload.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.kimetsunoyaibareload.world.KnyrTransparentWorldGameRule;
import net.mcreator.kimetsunoyaibareload.potion.TransparentWorldPotionEffect;
import net.mcreator.kimetsunoyaibareload.potion.TransparentWorldCooldownPotionEffect;
import net.mcreator.kimetsunoyaibareload.KnyrMod;

import java.util.Map;
import java.util.Collection;

public class SeeThroughWorldPressedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				KnyrMod.LOGGER.warn("Failed to load dependency world for procedure SeeThroughWorldPressed!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				KnyrMod.LOGGER.warn("Failed to load dependency entity for procedure SeeThroughWorldPressed!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (world.getWorldInfo().getGameRulesInstance().getBoolean(KnyrTransparentWorldGameRule.gamerule)) {
			if ((new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == TransparentWorldPotionEffect.potion)
								return true;
						}
					}
					return false;
				}
			}.check(entity)) == false && (new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == TransparentWorldCooldownPotionEffect.potion)
								return true;
						}
					}
					return false;
				}
			}.check(entity)) == false) {
				if (((entity instanceof ServerPlayerEntity) && (entity.world instanceof ServerWorld))
						? ((ServerPlayerEntity) entity).getAdvancements()
								.getProgress(((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
										.getAdvancement(new ResourceLocation("kimetsunoyaiba:transparent_world")))
								.isDone()
						: false) {
					if ((new Object() {
						boolean check(Entity _entity) {
							if (_entity instanceof LivingEntity) {
								Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
								for (EffectInstance effect : effects) {
									if (effect.getPotion() == net.mcreator.kimetsunoyaiba.potion.PotionDemonSlayerMarkPotionEffect.potion)
										return true;
								}
							}
							return false;
						}
					}.check(entity)) == true) {
						if (entity instanceof LivingEntity)
							((LivingEntity) entity).addPotionEffect(new EffectInstance(TransparentWorldPotionEffect.potion, (int) 2400, (int) 1));
					}
				}
			}
		}
	}
}
