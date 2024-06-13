package qc.suky.rush;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.FileUtil;
import qc.suky.rush.event.ArenaUnloadEvent;
import qc.suky.rush.objects.ArenaSpawner;
import qc.suky.rush.objects.ArenaTeam;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.codehaus.plexus.util.FileUtils.copyDirectory;
import static org.codehaus.plexus.util.FileUtils.copyFile;

public class RushArena {
	public RushArenaState status;

	private final File sourceWorldFolder;
	private File activeWorldFolder;

	private World bukkitWorld;
	private final Rush plugin;

	public final List<ArenaTeam> teams = new ArrayList<>();
	public final List<ArenaSpawner> spawners = new ArrayList<>();
	public Location lobbyPosition;

	@Getter
	private String name;

	@Getter
	private final List<Player> players = new ArrayList<>();

	public RushArena(File worldFolder, String worldName, boolean loadOnInit, Rush plugin) {
		this.sourceWorldFolder = new File(
				worldFolder,
				worldName
		);

		this.plugin = plugin;

		name = worldName;



		if (loadOnInit) load();
	}

	public static void copyDirectoryCompatibityMode(File source, File destination) throws IOException {
		if (source.isDirectory()) {
			copyDirectory(source, destination);
		} else {
			copyFile(source, destination);
		}
	}

	public World getBukkitWorld() {
		if (!isLoaded()) {
			plugin.getLogger().warning("Attempting to get Bukkit World from unloaded Rush Arena");
			// Let's still try if it works...
		}
		return bukkitWorld;
	}

	public boolean load() {
		if (isLoaded()) return true;

		this.activeWorldFolder = new File(
				Bukkit.getWorldContainer().getParentFile(),
				sourceWorldFolder.getName() + "_active_" + System.currentTimeMillis()
		);

		try {
			if (!activeWorldFolder.exists())
				activeWorldFolder.mkdir();
			for (String f : sourceWorldFolder.list()) {
				copyDirectoryCompatibityMode(new File(sourceWorldFolder, f), new File(activeWorldFolder, f));
			}
		} catch (Exception e) {
			plugin.getLogger().severe("Failed to load Rush Map from source folder " + sourceWorldFolder);
			e.printStackTrace();
			return false;
		}

		this.bukkitWorld = Bukkit.createWorld(
				new WorldCreator(activeWorldFolder.getName())
		);

		if (bukkitWorld != null)  {
			this.bukkitWorld.setAutoSave(false);
			lobbyPosition = new Location(bukkitWorld, 0, 80, 0);
		}

		if (isLoaded()) {
			// update(bukkitWorld);
			return true;
		}

		return isLoaded();
	}

	public boolean isLoaded() {
		return bukkitWorld != null;
	}

	public void unload() {
		ArenaUnloadEvent event = new ArenaUnloadEvent(this);
		Bukkit.getServer().getPluginManager().callEvent(event);
		if (event.isCancelled()) return;

		if (bukkitWorld != null) Bukkit.unloadWorld(bukkitWorld, false);
		if (activeWorldFolder != null) activeWorldFolder.delete();

		bukkitWorld = null;
		activeWorldFolder = null;
	}


	/*
	Dangerous Function
	USE unload() WHEN YOU CAN, ONLY USE THIS FUNCTION IF IT FAILS.
	Reason: it has no check for nulls.
	*/
	public void forceUnload()
	{
		Bukkit.unloadWorld(bukkitWorld, false);

		if (!(activeWorldFolder.delete()))
		{
			plugin.getLogger().severe("Couldn't delete the active world folder.");
		}

		activeWorldFolder.deleteOnExit();

		bukkitWorld = null;
		activeWorldFolder = null;
	}

	public boolean restoreFromSource() {
		unload();
		return load();
	}

	public static void delete(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files == null) return;
			for (File child : files) {
				delete(child);
			}
		}
		file.delete();
	}

	public void start()
	{
		status = RushArenaState.RUNNING;
		int teamSize = teams.size();
		if (teamSize == 0)
		{
			Bukkit.broadcast(Format.format("<red>No teams were created, do /rush-admin addTeam <teamName> <hexColour>"));
			return;
		}
		int index = 0;
	}

	public void addPlayer(Player player) {
		if(!isLoaded()) load();
		Location loc = lobbyPosition;
		player.teleport(loc);
		players.add(player);
		if (players.size() >= 4)
		{
			start();
		}
	}

	public void removePlayer(Player player) {
		players.remove(player);
		World to = Bukkit.getWorld(Objects.requireNonNull(plugin.getConfig().getString("spawn_world")));
		player.teleport(to.getSpawnLocation());
	}

}