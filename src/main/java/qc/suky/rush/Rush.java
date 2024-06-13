package qc.suky.rush;

import co.aikar.commands.PaperCommandManager;
import de.exlll.configlib.NameFormatters;
import de.exlll.configlib.YamlConfigurationProperties;
import de.exlll.configlib.YamlConfigurations;
import io.papermc.paper.math.BlockPosition;
import io.papermc.paper.math.FinePosition;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import qc.suky.rush.command.RushAdminCommand;
import qc.suky.rush.command.RushCommand;
import qc.suky.rush.config.Settings;
import qc.suky.rush.listener.AppendPlayerAmount;
import qc.suky.rush.listener.HandleArena;
import qc.suky.rush.objects.ArenaSpawner;
import qc.suky.rush.objects.ArenaTeam;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class Rush extends JavaPlugin {
	@Getter
	private final List<RushArena> arenas = new ArrayList<>();

	private PaperCommandManager commandManager;

	@NotNull
	YamlConfigurationProperties.Builder<?> YAML_CONFIGURATION_PROPERTIES = YamlConfigurationProperties.newBuilder()
			.charset(StandardCharsets.UTF_8)
			.setNameFormatter(NameFormatters.LOWER_UNDERSCORE);

	@Setter
	@Getter
	private Settings settings;


	@Override
	public void onEnable() {
		// Plugin startup logic
		commandManager = new PaperCommandManager(this);

		setSettings(YamlConfigurations.update(
				getDataFolder().toPath().resolve("config.yml"),
				Settings.class,
				YAML_CONFIGURATION_PROPERTIES.header("""
						┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
						┃       BungeeMT Config        ┃
						┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""").build()
		));

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
		getSettings().getArenas().forEach(ar -> getLogger().info(ar.getName())); //debug
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