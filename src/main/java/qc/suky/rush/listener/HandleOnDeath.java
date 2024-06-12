package qc.suky.rush.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import qc.suky.rush.Rush;

public class HandleOnDeath implements Listener {
    private final Rush plugin;

    public HandleOnDeath(Rush plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void OnDeath(PlayerDeathEvent e)
    {
        // TODO if (e.)
    }

    @EventHandler
    public void OnRespawn(PlayerRespawnEvent e)
    {

    }
}
