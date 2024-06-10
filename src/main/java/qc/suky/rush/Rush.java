package qc.suky.rush;

import org.bukkit.plugin.java.JavaPlugin;

public final class Rush extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println(Format.format("&4&lRUSH Plugin &chas been enabled."));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println(Format.format("&4&lRUSH Plugin &chas been disabled."));
    }
}
