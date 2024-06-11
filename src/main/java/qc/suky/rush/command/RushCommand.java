package qc.suky.rush.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import qc.suky.rush.Format;
import qc.suky.rush.Rush;

@CommandAlias("rush")
@AllArgsConstructor
public class RushCommand extends BaseCommand {
	private final Rush plugin;

	@Default
	@CommandPermission("rush.use")
	public void onDefault(CommandSender player) {
		player.sendMessage(Format.format("<white>Not implemented yet!"));
	}

	@Subcommand("teleport")
	@CommandPermission("rush.use")
	public void onTeleport(Player player) {
		Location loc = new Location(plugin.getArena().getBukkitWorld(), 0, 65, 0);
		player.teleport(loc);
		player.sendMessage(Format.format("<green>Teleporting to the arena...</green>"));
	}
}
