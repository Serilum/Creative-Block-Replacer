package com.natamus.creativeblockreplacer.forge.events;

import com.natamus.creativeblockreplacer.events.ReplaceEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeReplaceEvent {
	public static void registerEventsInBus() {
		BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeReplaceEvent.class);
	}

	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Pre e) {
		Player player = e.player();
		Level level = player.level();
		if (level.isClientSide()) {
			return;
		}

		ReplaceEvent.onPlayerTick((ServerLevel)level, (ServerPlayer)player);
	}
	
	@SubscribeEvent
	public static boolean onBlockClick(PlayerInteractEvent.RightClickBlock e) {
		if (!ReplaceEvent.onBlockClick(e.getLevel(), e.getEntity(), e.getHand(), e.getPos(), e.getHitVec())) {
			return true;
		}
		return false;
	}
}
