package de.jumpingpxl.labymod.nobob.listener;

import de.jumpingpxl.labymod.nobob.util.Settings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Objects;

public class TickListener {

	private final Settings settings;

	public TickListener(Settings settings) {
		this.settings = settings;
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		Minecraft minecraft = Minecraft.getMinecraft();
		if (settings.isEnabled() && Objects.nonNull(minecraft.player)) {
			minecraft.player.distanceWalkedModified = 0.0F;
		}
	}
}
