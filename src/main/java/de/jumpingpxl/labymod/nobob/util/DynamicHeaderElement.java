package de.jumpingpxl.labymod.nobob.util;

import net.labymod.main.LabyMod;
import net.labymod.settings.elements.SettingsElement;

public class DynamicHeaderElement extends SettingsElement {

	private final DynamicConsumer<Boolean> dynamicConsumer;
	private final String[] lines;
	private final int lineHeight;

	public DynamicHeaderElement(DynamicConsumer<Boolean> dynamicConsumer, int lineHeight,
	                            String... lines) {
		super("", null);

		this.dynamicConsumer = dynamicConsumer;
		this.lineHeight = lineHeight;
		this.lines = lines;
	}

	@Override
	public void draw(int x, int y, int maxX, int maxY, int mouseX, int mouseY) {
		if (dynamicConsumer.accept()) {
			super.draw(x, y, maxX, maxY, mouseX, mouseY);
			double absoluteY = y + lineHeight / 2D;
			for (String line : lines) {
				LabyMod.getInstance().getDrawUtils().drawCenteredString(line, x + (maxX - x) / 2D,
						absoluteY, 1D);
				absoluteY += lineHeight;
			}
		}
	}

	@Override
	public void drawDescription(int i, int i1, int i2) {

	}

	@Override
	public void mouseClicked(int i, int i1, int i2) {

	}

	@Override
	public void mouseRelease(int i, int i1, int i2) {

	}

	@Override
	public void mouseClickMove(int i, int i1, int i2) {

	}

	@Override
	public void keyTyped(char c, int i) {

	}

	@Override
	public void unfocus(int i, int i1, int i2) {

	}

	@Override
	public int getEntryHeight() {
		return lines.length * lineHeight;
	}

	@FunctionalInterface
	public interface DynamicConsumer<T> {

		T accept();
	}
}
