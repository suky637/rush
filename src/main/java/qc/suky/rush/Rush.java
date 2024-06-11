package qc.suky.rush;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import qc.suky.rush.command.RushAdminCommand;
import qc.suky.rush.command.RushCommand;
import qc.suky.rush.listener.AppendPlayerAmount;
import qc.suky.rush.listener.HandleArena;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Rush extends JavaPlugin {
	@Getter
	private final List<RushArena> arenas = new ArrayList<>();

	private PaperCommandManager commandManager;

	@Override
	public void onEnable() {
		// Plugin startup logic
		commandManager = new PaperCommandManager(this);

		getConfig().options().copyDefaults();
		saveDefaultConfig();

		File gameMapsFolder = new File(getDataFolder(), "gameMaps");
		if (!gameMapsFolder.exists()) {
			gameMapsFolder.mkdirs();
		}

		getServer().getPluginManager().registerEvents(new HandleArena(this), this);
		getServer().getPluginManager().registerEvents(new AppendPlayerAmount(this), this);
		//arenas.add(new RushArena(gameMapsFolder, "rush", true, this));

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
