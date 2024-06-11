package qc.suky.rush;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class Format {

    /**
     * Format a string with MiniMessage
     * @param arg The string to format
     * @return The formatted string as a Component
     */
	public static Component format(String arg) {
		return MiniMessage.miniMessage().deserialize(arg);
	}

    /**
	 * Read this:
     * <a href="https://docs.advntr.dev/faq.html#how-can-i-support-both-minimessage-and-legacy-code-formatting">How can I support both MiniMessage and legacy (§-code) formatting?</a>
	 * @param arg The string to format
	 * @return The formatted string as a Component
	 */
    public static Component formatLegacy(String arg) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(arg);
    }

    /**
     * Format a string with a horrible way, a person should never use this function
     * I hope you understand that this is NOT a joke. This is a serious warning.
     * Please refrain from using this function. It is a bad practice. Use MiniMessage API instead.
     * @param arg The string to format
     * @return The formatted string as a String
     */
    public static String formatHorrible(String arg) {
        return arg.replace("&", "§");
    }

}
