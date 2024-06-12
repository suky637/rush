package qc.suky.rush.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import qc.suky.rush.Format;
import qc.suky.rush.Rush;
import qc.suky.rush.RushArena;
import qc.suky.rush.RushArenaState;
import qc.suky.rush.util.GuiHelper;

import java.util.List;
import java.util.Random;

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
	public void onTeleport(Player player, String name) {
		List<RushArena> arenas = plugin.getArenas();
		for (RushArena arena : arenas) {
			if (arena.status == RushArenaState.RUNNING || arena.status == RushArenaState.FINISHED)
				continue;
			if (arena.getBukkitWorld().getName().equalsIgnoreCase(name)) {
				World world = arena.getBukkitWorld();
				Location location = new Location(world, 0, 65, 0);
				player.teleport(location);
				player.sendMessage(Format.format("<green>Teleporting to the arena...</green>"));
				return;
			}
		}
	}

	@Subcommand("join")
	@CommandPermission("rush.use")
	public void onJoin(Player player) {
		List<RushArena> arenas = plugin.getArenas();
		Random random = new Random();

		if (arenas.isEmpty()) {
			player.sendMessage(Format.format("<red>No arenas available to join.</red>"));
			return;
		}

		int randomIndex = random.nextInt(arenas.size());
		RushArena randomArena = arenas.get(randomIndex);

		if (randomArena.status == RushArenaState.RUNNING || randomArena.status == RushArenaState.FINISHED) {
			player.sendMessage(Format.format("<red>The selected arena is not available to join.</red>"));
			return;
		}

		randomArena.addPlayer(player);
		player.sendMessage(Format.format("<green>Joined the arena " + randomArena.getBukkitWorld().getName() + "</green>"));
	}

	@Subcommand("menu")
	@CommandPermission("rush.use")
	public void onMenu(Player player) {
		ChestGui gui = new ChestGui(6, "Rush");
		gui.setOnGlobalClick(event -> event.setCancelled(true));
		GuiHelper.addOuter(gui);

		OutlinePane effectPane = new OutlinePane(1, 1, 8, 5);

		for(RushArena arena : plugin.getArenas()) {
			effectPane.addItem(GuiHelper.createGuiItem(arena));
		}

		gui.show(player);
	}


}
