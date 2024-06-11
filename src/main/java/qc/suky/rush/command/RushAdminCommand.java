package qc.suky.rush.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import lombok.AllArgsConstructor;
import org.bukkit.command.CommandSender;
import qc.suky.rush.Format;
import qc.suky.rush.Rush;

@CommandAlias("rush-admin")
@AllArgsConstructor
public class RushAdminCommand extends BaseCommand {
	private final Rush plugin;

	@Default
	@CommandPermission("rush.admin")
	private void onDefault(CommandSender sender) {
		sender.sendMessage(Format.format("<white>Not implemented yet!"));
	}

	@Subcommand("list")
	@CommandPermission("rush.admin")
	private void onArenaList(CommandSender sender) {
		String name = plugin.getArena().getBukkitWorld().getName();
		sender.sendMessage(Format.format("List: <green>%s", name));
	}

}
