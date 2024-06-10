package qc.suky.rush;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.util.FileUtil;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RushArena {
    public RushArenaState status;
    public List<Block> blocks = new ArrayList<>();

    private final File sourceWorldFolder;
    private File activeWorldFolder;

    private World bukkitWorld;

    public RushArena(File worldFolder, String worldName, boolean loadOnInit)
    {
        this.sourceWorldFolder = new File(
                worldFolder,
                worldName
        );

        if (loadOnInit) load();
    }

    public boolean load() {
        if (isLoaded()) return true;

        this.activeWorldFolder = new File(
                Bukkit.getWorldContainer().getParentFile(),
                sourceWorldFolder.getName() + "_active_" + System.currentTimeMillis()
        );

        try {
            FileUtil.copy(sourceWorldFolder, activeWorldFolder);
        } catch (Exception e) {
            Bukkit.getLogger().severe("Failed to load Rush Map from source folder " + sourceWorldFolder);
            e.printStackTrace();
            return false;
        }

        this.bukkitWorld = Bukkit.createWorld(
                new WorldCreator(activeWorldFolder.getName())
        );

        if (bukkitWorld != null) this.bukkitWorld.setAutoSave(false);
        return isLoaded();
    }

    public boolean isLoaded()
    {
        return bukkitWorld != null;
    }

    public void unload()
    {
        if (bukkitWorld != null) Bukkit.unloadWorld(bukkitWorld, false);
        if (activeWorldFolder != null) activeWorldFolder.delete();

        bukkitWorld = null;
        activeWorldFolder = null;
    }

    public boolean restoreFromSource()
    {
        unload();
        return load();
    }

    public static void delete(File file)
    {
        if (file.isDirectory())
        {
            File[] files = file.listFiles();
            if (files == null) return;
            for (File child : files)
            {
                delete(child);
            }
        }
        file.delete();
    }

}
