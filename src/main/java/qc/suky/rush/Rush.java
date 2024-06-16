package qc.suky.rush;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;
import qc.suky.rush.command.RushAdminCommand;
import qc.suky.rush.command.RushCommand;
import qc.suky.rush.listener.AppendPlayerAmount;
import qc.suky.rush.listener.HandleArena;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Rush extends JavaPlugin {
	@Getter
	private final List<RushArena> arenas = new ArrayList<>();

	public static Rush instance;

	private PaperCommandManager commandManager;

	JSONObject config;


	public String readFile(String path)
	{
		String data = "";
		try {
			File myObj = new File(path);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data += myReader.nextLine();
				System.out.println(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public void onEnable() {
		// Plugin startup logic
		commandManager = new PaperCommandManager(this);
		instance = this;

		// TODO: Testing if the file doesn't exist, if it does, create one with default values.
		config = new JSONObject(readFile(getDataFolder().toPath().toAbsolutePath() + "/config.json"));

		getConfig().options().copyDefaults(true);
		saveDefaultConfig();

		File gameMapsFolder = new File(getDataFolder(), "gameMaps");
		getLogger().info("&eLoading Maps...");
		gameMapsFolder.mkdirs();
		for (File file : gameMapsFolder.listFiles()) {
			getLogger().info("&eCreating an arena");
			arenas.add(new RushArena(gameMapsFolder, file.getName(), true, this));
		}
		getLogger().info("&eFinished Loading Maps");

		getServer().getPluginManager().registerEvents(new HandleArena(this), this);
		getServer().getPluginManager().registerEvents(new AppendPlayerAmount(this), this);

		commandManager.registerCommand(new RushCommand(this));
		commandManager.registerCommand(new RushAdminCommand(this));
		getLogger().info("Plugin has been enabled.");
		//getSettings().getArenas().forEach(ar -> getLogger().info(ar.getName())); //debug
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
		for (RushArena arena : arenas) {
			arena.forceUnload();
		}

		getLogger().info("Plugin has been disabled.");
	}

}