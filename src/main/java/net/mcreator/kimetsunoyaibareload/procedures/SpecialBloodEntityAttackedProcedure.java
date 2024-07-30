package net.mcreator.kimetsunoyaibareload.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.kimetsunoyaibareload.world.KnyrSpecialBloodGameRule;
import net.mcreator.kimetsunoyaibareload.KnyrMod;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class SpecialBloodEntityAttackedProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntityAttacked(LivingHurtEvent event) {
			if (event != null && event.getEntity() != null) {
				Entity entity = event.getEntity();
				Entity sourceentity = event.getSource().getTrueSource();
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				double amount = event.getAmount();
				World world = entity.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("amount", amount);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("sourceentity", sourceentity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				KnyrMod.LOGGER.warn("Failed to load dependency world for procedure SpecialBloodEntityAttacked!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				KnyrMod.LOGGER.warn("Failed to load dependency entity for procedure SpecialBloodEntityAttacked!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				KnyrMod.LOGGER.warn("Failed to load dependency sourceentity for procedure SpecialBloodEntityAttacked!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if (world.getWorldInfo().getGameRulesInstance().getBoolean(KnyrSpecialBloodGameRule.gamerule)) {
			new Object() {
				private int ticks = 0;
				private float waitTicks;
				private IWorld world;

				public void start(IWorld world, int waitTicks) {
					this.waitTicks = waitTicks;
					MinecraftForge.EVENT_BUS.register(this);
					this.world = world;
				}

				@SubscribeEvent
				public void tick(TickEvent.ServerTickEvent event) {
					if (event.phase == TickEvent.Phase.END) {
						this.ticks += 1;
						if (this.ticks >= this.waitTicks)
							run();
					}
				}

				private void run() {
					if (sourceentity.getPersistentData().getBoolean("oni") && entity.getPersistentData().getBoolean("knyrSpecialBlood")) {
						if (8 >= MathHelper.nextInt(new Random(), 1, 10)) {
							sourceentity.attackEntityFrom(DamageSource.MAGIC, (float) 15);
						}
						if (7 >= MathHelper.nextInt(new Random(), 1, 10)) {
							if (sourceentity instanceof LivingEntity)
								((LivingEntity) sourceentity)
										.addPotionEffect(new EffectInstance(Effects.BLINDNESS, (int) 60, (int) 5, (false), (false)));
						}
						if (6 >= MathHelper.nextInt(new Random(), 1, 10)) {
							if (sourceentity instanceof LivingEntity)
								((LivingEntity) sourceentity)
										.addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 60, (int) 4, (false), (false)));
						}
						if (5 >= MathHelper.nextInt(new Random(), 1, 10)) {
							if (sourceentity instanceof LivingEntity)
								((LivingEntity) sourceentity)
										.addPotionEffect(new EffectInstance(Effects.WEAKNESS, (int) 60, (int) 2, (false), (false)));
						}
						if (4 >= MathHelper.nextInt(new Random(), 1, 10)) {
							if (sourceentity instanceof LivingEntity)
								((LivingEntity) sourceentity)
										.addPotionEffect(new EffectInstance(Effects.NAUSEA, (int) 60, (int) 200, (false), (false)));
						}
					}
					MinecraftForge.EVENT_BUS.unregister(this);
				}
			}.start(world, (int) 1);
		}
	}
}
