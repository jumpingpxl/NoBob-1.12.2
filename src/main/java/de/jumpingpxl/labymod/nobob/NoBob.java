package de.jumpingpxl.labymod.nobob;

import de.jumpingpxl.labymod.nobob.listener.LoginServerListener;
import de.jumpingpxl.labymod.nobob.listener.MessageSendListener;
import de.jumpingpxl.labymod.nobob.listener.TickListener;
import de.jumpingpxl.labymod.nobob.util.Settings;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.SettingsElement;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

import java.util.List;

/**
 * @author Nico (JumpingPxl) Middendorf
 * @date 02.06.2021
 * @project LabyMod-Addon: NoBob-1.12.2
 */

public class NoBob extends LabyModAddon {

	public static final String VERSION = "2";

	private Settings settings;

	@Override
	public void onEnable() {
		settings = new Settings(this);

		getApi().getEventManager().registerOnJoin(new LoginServerListener(this));
		getApi().getEventManager().register(new MessageSendListener(settings));
		getApi().registerForgeListener(new TickListener(settings));

		notifyAboutDeactivatedBobbing();
	}

	@Override
	public void loadConfig() {
		settings.loadConfig();
	}

	@Override
	protected void fillSettings(List<SettingsElement> settingsElements) {
		settings.fillSettings(settingsElements);
	}

	public void notifyAboutDeactivatedBobbing() {
		if (!settings.isEnabled()) {
			return;
		}

		Minecraft minecraft = Minecraft.getMinecraft();
		if (!minecraft.gameSettings.viewBobbing) {
			ITextComponent textComponent = new TextComponentString(
					"§7[§eNoBob§7] §4§lIMPORTANT: §cIn order for NoBob to work, the following setting has to"
							+ " be enabled: ");
			ITextComponent settingComponent = new TextComponentString(
					"§cOptions -> Video Settings -> View Bobbing §l[CLICK]");

			Style style = settingComponent.getStyle();
			style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new TextComponentString("§cClick to enable bobbing")));
			style.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "+nobob disablebobbing"));
			settingComponent.setStyle(style);
			textComponent.appendSibling(settingComponent);
			minecraft.ingameGUI.getChatGUI().printChatMessage(textComponent);
		}
	}
}
