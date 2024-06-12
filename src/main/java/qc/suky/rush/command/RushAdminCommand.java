package qc.suky.rush.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import net.william278.husktowns.menu.ColorPicker;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import qc.suky.rush.Format;
import qc.suky.rush.Rush;
import qc.suky.rush.RushArena;
import qc.suky.rush.objects.ArenaTeam;

import javax.annotation.Nullable;
import java.util.List;

@CommandAlias("rush-admin")
public class RushAdminCommand extends BaseCommand {
	private final Rush plugin;


	public RushAdminCommand(Rush plugin)
	{
		this.plugin = plugin;
	}

	RushArena currentArena;

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

	@Nullable
	private RushArena findArena(String arenaName)
	{
		for (RushArena arena : plugin.getArenas())
			if (arena.getName().equals(arenaName))
				return arena;
		return null;
	}

	@Subcommand("edit")
	@CommandPermission("rush.admin")
	private void OnArenaEdit(Player player, String arenaName)
	{
		currentArena = findArena(arenaName);
		if (currentArena == null)
			player.sendMessage(Format.format("<red>This arena doesn't exist."));
		player.sendMessage(Format.format("<green>Arena " + currentArena.getName() + " is selected!"));
	}

	@Subcommand("setLobby")
	@CommandPermission("rush.admin")
	private void onArenaSetLobby(Player player) {
		if (currentArena == null)
			player.sendMessage(Format.format("<red>Please select an arena by doing \"/rush-admin edit <World Name>\""));
		currentArena.lobbyPosition = new Location(player.getWorld(), player.getX(), player.getY(), player.getZ());
		player.sendMessage(Format.format("<green>Set Lobby Position to X: " + player.getX() + "; Y: " + player.getY() + "; Z: " + player.getZ() + "."));
	}

	private ArenaTeam getTeamByName(String teamName) {
		for (ArenaTeam team : currentArena.teams) {
			if (team.getTeamName().equals(teamName)) {
				return team;
			}
		}
		return null;
	}

	@Subcommand("color")
	private void onArenaAddTeamColorPicker(Player player, String teamName) {
		if (currentArena == null)
			player.sendMessage(Format.format("<red>Please select an arena by doing \"/rush-admin edit <World Name>\""));

		ArenaTeam team = getTeamByName(teamName);
		if (team == null) {
			player.sendMessage(Format.format("<red>Team with name '" + teamName + "' does not exist."));
			return;
		}

		player.sendMessage(ColorPicker.builder().command("/rush:rush-admin color " + teamName).build().toComponent());
	}

	@Subcommand("color")
	private void onArenaAddTeamColorPicker(Player player, String teamName, String color) {
		if (currentArena == null)
			player.sendMessage(Format.format("<red>Please select an arena by doing \"/rush-admin edit <World Name>\""));

		ArenaTeam team = getTeamByName(teamName);
		if (team == null) {
			player.sendMessage(Format.format("<red>Team with name '" + teamName + "' does not exist."));
			return;
		}

		if (color.startsWith("#")) {
			color = color.substring(1);
		}
		int colourValue = Integer.parseInt(color, 16);
		team.setColor(Color.fromRGB(colourValue));
		player.sendMessage(Format.format("<color:#" + color + ">Set color for team "+ teamName));
	}

	@Subcommand("addTeam")
	@CommandPermission("rush.admin")
	private void onArenaAddTeam(Player player, String name) {
		if (currentArena == null)
			player.sendMessage(Format.format("<red>Please select an arena by doing \"/rush-admin edit <World Name>\""));
		int colourValue = Integer.parseInt("FF0000", 16);
		ArenaTeam team = new ArenaTeam();
		team.setColor(Color.fromRGB(colourValue));
		team.setTeamName(name);
		currentArena.teams.add(team);
		player.sendMessage(Format.format("<green>Added Team <white>'" + team.getTeamName() + "'"));
		player.sendMessage(Format.format("<green>Use <white>/rush-admin color <teamName> <color> <green>to set the team color."));
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