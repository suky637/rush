package qc.suky.rush.command;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import qc.suky.rush.Format;

public class RushCommand implements CommandExecutor {


	private static World arena;

	public RushCommand() {

	}

	public static void update(World world) {
		arena = world;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		if (commandSender.hasPermission("rush.use") && commandSender instanceof Player) {
			Player player = (Player) commandSender;
			Location loc = new Location(arena, 0, 65, 0);
			player.teleport(loc);
			player.sendMessage(Format.format("<green>Teleporting to the arena...</green>"));
		}
		return false;
	}
}
