package de.jumpingpxl.labymod.nobob.listener;

import de.jumpingpxl.labymod.nobob.util.ChatComponent;
import de.jumpingpxl.labymod.nobob.util.Settings;
import net.labymod.api.events.MessageSendEvent;
import net.minecraft.client.Minecraft;

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

			ChatComponent chatComponent = ChatComponent.create("[")
					.setColor(ChatComponent.Color.GRAY)
					.append("NoBob")
					.setColor(ChatComponent.Color.YELLOW)
					.append("] ")
					.setColor(ChatComponent.Color.GRAY)
					.append("Successfully enabled bobbing. NoBob will now work like expected.")
					.setColor(ChatComponent.Color.GREEN);

			minecraft.ingameGUI.getChatGUI().printChatMessage(chatComponent.build());
		}

		return true;
	}
}
