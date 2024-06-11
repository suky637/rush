package qc.suky.rush;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import qc.suky.rush.command.RushAdminCommand;
import qc.suky.rush.command.RushCommand;
import qc.suky.rush.listener.AppendPlayerAmount;
import qc.suky.rush.listener.HandleArena;

import java.io.File;
import java.util.Objects;

public final class Rush extends JavaPlugin {
	@Getter
	private RushArena arena; // rn only implementing for 1 arena, TODO: adding multiple arenas
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

		arena = new RushArena(gameMapsFolder, "rush", true, this);

		commandManager.registerCommand(new RushCommand(this));
		commandManager.registerCommand(new RushAdminCommand(this));
		getLogger().info("Plugin has been enabled.");
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
		if (arena != null)
			arena.unload();
		getLogger().info("Plugin has been disabled.");
	}

}
