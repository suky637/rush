package qc.suky.rush;

import org.bukkit.plugin.java.JavaPlugin;
import qc.suky.rush.command.RushAdminCommand;
import qc.suky.rush.command.RushCommand;

import java.io.File;
import java.util.Objects;

public final class Rush extends JavaPlugin {
	private RushArena arena; // rn only implementing for 1 arena, TODO: adding multiple arenas

	@Override
	public void onEnable() {
		// Plugin startup logic
		getDataFolder().mkdirs();

		File gameMapsFolder = new File(getDataFolder(), "gameMaps");
		if (!gameMapsFolder.exists()) {
			gameMapsFolder.mkdirs();
		}

		arena = new RushArena(gameMapsFolder, "rush", true, this);


		Objects.requireNonNull(getCommand("rush")).setExecutor(new RushCommand());
		Objects.requireNonNull(getCommand("rush-admin")).setExecutor(new RushAdminCommand());

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
