package qc.suky.rush;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Format {

	public static Component format(String arg) {
		return MiniMessage.miniMessage().deserialize(arg);
	}

}
