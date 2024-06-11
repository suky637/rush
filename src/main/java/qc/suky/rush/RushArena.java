package qc.suky.rush;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import qc.suky.rush.command.RushCommand;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.codehaus.plexus.util.FileUtils.copyDirectory;
import static org.codehaus.plexus.util.FileUtils.copyFile;

public class RushArena {
	public RushArenaState status;
	public List<Block> blocks = new ArrayList<>();

	private final File sourceWorldFolder;
	private File activeWorldFolder;

	private World bukkitWorld;
	private final Rush plugin;

	public RushArena(File worldFolder, String worldName, boolean loadOnInit, Rush plugin) {
		this.sourceWorldFolder = new File(
				worldFolder,
				worldName
		);

		this.plugin = plugin;

		if (loadOnInit) load();
	}

	public static void copyDirectoryCompatibityMode(File source, File destination) throws IOException {
		if (source.isDirectory()) {
			copyDirectory(source, destination);
		} else {
			copyFile(source, destination);
		}
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

		if (bukkitWorld != null) this.bukkitWorld.setAutoSave(false);

		if (isLoaded()) {
			RushCommand.update(bukkitWorld);
			return true;
		}
		return isLoaded();
	}

	public boolean isLoaded() {
		return bukkitWorld != null;
	}

	public void unload() {
		if (bukkitWorld != null) Bukkit.unloadWorld(bukkitWorld, false);
		if (activeWorldFolder != null) activeWorldFolder.delete();

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

}
