package qc.suky.rush;

import co.aikar.commands.PaperCommandManager;
import io.papermc.paper.math.BlockPosition;
import io.papermc.paper.math.FinePosition;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import qc.suky.rush.command.RushAdminCommand;
import qc.suky.rush.command.RushCommand;
import qc.suky.rush.listener.AppendPlayerAmount;
import qc.suky.rush.listener.HandleArena;
import qc.suky.rush.objects.ArenaSpawner;
import qc.suky.rush.objects.ArenaTeam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Rush extends JavaPlugin {
	@Getter
	private final List<RushArena> arenas = new ArrayList<>();

	private PaperCommandManager commandManager;

	public final List<ArenaTeam> teams = new ArrayList<>();
	public final List<ArenaSpawner> spawners = new ArrayList<>();
	public Location lobbyPosition;


	@Override
	public void onEnable() {
		// Plugin startup logic
		commandManager = new PaperCommandManager(this);

		getConfig().options().copyDefaults();
		saveDefaultConfig();

		File gameMapsFolder = new File(getDataFolder(), "gameMaps");
		if (!gameMapsFolder.exists()) {
			gameMapsFolder.mkdirs();
			for (File file : gameMapsFolder.listFiles()) {
				arenas.add(new RushArena(gameMapsFolder, file.getName(), false, this));
			}
		}

		getServer().getPluginManager().registerEvents(new HandleArena(this), this);
		getServer().getPluginManager().registerEvents(new AppendPlayerAmount(this), this);

		commandManager.registerCommand(new RushCommand(this));
		commandManager.registerCommand(new RushAdminCommand(this));
		getLogger().info("Plugin has been enabled.");
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
		for (RushArena arena : arenas) {
			if (arena != null)
				arena.unload();
		}

		getLogger().info("Plugin has been disabled.");
	}

}
