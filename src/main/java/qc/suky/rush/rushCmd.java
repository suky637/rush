package qc.suky.rush;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class rushCmd implements CommandExecutor {


    private static World arena;

    public rushCmd()
    {

    }

    public static void update(World world)
    {
        arena = world;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.hasPermission("rush.use") && commandSender instanceof Player)
        {
            Player plr = (Player) commandSender;
            Location loc = new Location(arena, 0, 65, 0);
            plr.teleport(loc);
        }
        return false;
    }
}
