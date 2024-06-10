package qc.suky.rush;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Rush extends JavaPlugin {

    private RushArena arena; // rn only implementing for 1 arena, TODO: adding multiple arenas

    @Override
    public void onEnable() {
        // Plugin startup logic

        getDataFolder().mkdirs();

        File gameMapsFolder = new File(getDataFolder(), "gameMaps");
        if (!gameMapsFolder.exists())
        {
            gameMapsFolder.mkdirs();
        }

        arena = new RushArena(gameMapsFolder, "rush", true);




        System.out.println("RUSH Plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("RUSH Plugin has been disabled.");
    }
}
