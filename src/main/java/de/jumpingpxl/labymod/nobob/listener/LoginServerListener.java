package de.jumpingpxl.labymod.nobob.listener;

import de.jumpingpxl.labymod.nobob.NoBob;
import net.labymod.utils.Consumer;
import net.labymod.utils.ServerData;

public class LoginServerListener implements Consumer<ServerData> {

	private final NoBob noBob;

	public LoginServerListener(NoBob noBob) {
		this.noBob = noBob;
	}

	@Override
	public void accept(ServerData serverData) {
		noBob.notifyAboutDeactivatedBobbing();
	}
}
