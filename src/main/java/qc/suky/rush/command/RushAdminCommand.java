package qc.suky.rush.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import qc.suky.rush.Format;

@CommandAlias("rush-admin")
public class RushAdminCommand extends BaseCommand {

	@Default
	private void onDefault(CommandSender sender) {
		sender.sendMessage(Format.format("<white>Not implemented yet!"));
	}

}
