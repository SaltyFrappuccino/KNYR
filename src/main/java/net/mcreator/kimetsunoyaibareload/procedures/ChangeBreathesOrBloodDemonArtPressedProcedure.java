package net.mcreator.kimetsunoyaibareload.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.kimetsunoyaibareload.world.KnyrTransparentWorldGameRule;
import net.mcreator.kimetsunoyaibareload.potion.TransparentWorldPotionEffect;
import net.mcreator.kimetsunoyaibareload.KnyrMod;

import java.util.Map;
import java.util.Collection;

public class ChangeBreathesOrBloodDemonArtPressedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				KnyrMod.LOGGER.warn("Failed to load dependency world for procedure ChangeBreathesOrBloodDemonArtPressed!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				KnyrMod.LOGGER.warn("Failed to load dependency entity for procedure ChangeBreathesOrBloodDemonArtPressed!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (world.getWorldInfo().getGameRulesInstance().getBoolean(KnyrTransparentWorldGameRule.gamerule)) {
			if (new Object() {
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
			}.check(entity)) {
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
						if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
								.getDouble("cooltime") > 0) {
							((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
									.putDouble("cooltime",
											(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
													.getOrCreateTag().getDouble("cooltime") / 2));
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 5);
			}
		}
	}
}
