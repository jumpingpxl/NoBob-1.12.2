package de.jumpingpxl.labymod.nobob.util;

import com.google.common.collect.Lists;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

import java.util.List;
import java.util.Objects;

public class ChatComponent {

	private final ChatComponent parent;
	private final Style chatStyle;
	private final String text;
	private final List<ChatComponent> siblings;

	private ChatComponent(ChatComponent parent, String text) {
		this.parent = parent;
		this.text = text;

		chatStyle = new Style();
		siblings = Lists.newArrayList();
	}

	public static ChatComponent create(String text) {
		return new ChatComponent(null, text);
	}

	public ChatComponent append(String text) {
		return append(new ChatComponent(this, text));
	}

	public ChatComponent setColor(Color color) {
		chatStyle.setColor(color.getColor());
		return this;
	}

	public ChatComponent setBold(boolean bold) {
		chatStyle.setBold(bold);
		return this;
	}

	public ChatComponent setHoverText(String text) {
		chatStyle.setHoverEvent(
				new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(text)));
		return this;
	}

	public ChatComponent setClickCommand(String command) {
		chatStyle.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
		return this;
	}

	private ChatComponent append(ChatComponent chatComponent) {
		if (Objects.nonNull(parent)) {
			return parent.append(chatComponent);
		}

		siblings.add(chatComponent);
		return chatComponent;
	}

	public ITextComponent build() {
		if (Objects.nonNull(parent)) {
			return parent.build();
		}

		ITextComponent parentComponent = new TextComponentString(text);
		parentComponent.setStyle(chatStyle);
		for (ChatComponent sibling : siblings) {
			ITextComponent siblingComponent = new TextComponentString(sibling.text);
			siblingComponent.setStyle(sibling.chatStyle);
			parentComponent.appendSibling(siblingComponent);
		}

		return parentComponent;
	}

	public enum Color {
		BLACK,
		DARK_BLUE,
		DARK_GREEN,
		DARK_AQUA,
		DARK_RED,
		DARK_PURPLE,
		GOLD,
		GRAY,
		DARK_GRAY,
		BLUE,
		GREEN,
		AQUA,
		RED,
		LIGHT_PURPLE,
		YELLOW,
		WHITE,
		OBFUSCATED,
		BOLD,
		STRIKETHROUGH,
		UNDERLINE,
		ITALIC,
		RESET;

		public TextFormatting getColor() {
			return TextFormatting.getValueByName(name());
		}
	}
}
