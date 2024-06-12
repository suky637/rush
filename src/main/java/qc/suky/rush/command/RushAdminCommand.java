package qc.suky.rush.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import io.papermc.paper.math.BlockPosition;
import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import qc.suky.rush.Format;
import qc.suky.rush.Rush;
import qc.suky.rush.objects.ArenaTeam;

import java.util.List;

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
		List<String> arenaNames = plugin.getArenas().stream().map(arena -> arena.getBukkitWorld().getName()).toList();
		sender.sendMessage(Format.format("List: <green>%s", arenaNames));
	}

	@Subcommand("setLobby")
	@CommandPermission("rush.admin")
	private void onArenaSetLobby(Player player) {
		plugin.lobbyPosition = new Location(null, player.getX(), player.getY(), player.getZ());
	}

	@Subcommand("addTeam")
	@CommandPermission("rush.admin")
	private void onArenaAddTeam(Player sender, String name, String colour) {
		// "/rush-admin addTeam <teamname> <teamcolour>
	}

	@Subcommand("setTeamSpawn")
	@CommandPermission("rush.admin")
	private void onArenaSetTeamSpawn(Player player, String team) {
		Location location = player.getLocation();
	}

	@Subcommand("addSpanwer bronze")
	@CommandPermission("rush.admin")
	private void onArenaAddBronzeSpawner(Player player) {
		Location location = player.getLocation();
	}

	@Subcommand("addSpanwer silver")
	@CommandPermission("rush.admin")
	private void onArenaAddSilverSpawner(Player player) {
		Location location = player.getLocation();
	}

	@Subcommand("addSpanwer gold")
	@CommandPermission("rush.admin")
	private void onArenaAddGoldSpawner(Player player) {
		Location location = player.getLocation();
	}

}
