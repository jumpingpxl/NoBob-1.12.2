package de.jumpingpxl.labymod.nobob.listener;

import de.jumpingpxl.labymod.nobob.util.Settings;
import net.labymod.api.events.MessageSendEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class MessageSendListener implements MessageSendEvent {

	private final Settings settings;

	public MessageSendListener(Settings settings) {
		this.settings = settings;
	}

	@Override
	public boolean onSend(String message) {
		if (!message.equals("+nobob disablebobbing")) {
			return false;
		}

		Minecraft minecraft = Minecraft.getMinecraft();
		if (settings.isEnabled() && !minecraft.gameSettings.viewBobbing) {
			minecraft.gameSettings.viewBobbing = true;
			ITextComponent textComponent = new TextComponentString(
					"§7[§eNoBob§7] §aSuccessfully enabled bobbing. NoBob will now work like expected.");
			minecraft.ingameGUI.getChatGUI().printChatMessage(textComponent);
		}

		return true;
	}
}
