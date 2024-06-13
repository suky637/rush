package qc.suky.rush.objects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import qc.suky.rush.Rush;
import qc.suky.rush.RushArena;

public class ArenaSpawner {
    public ArenaSpawnerType type;

    public Location location;

    private final RushArena arena;

    public ArenaSpawner(RushArena arena, ArenaSpawnerType type, double x, double y, double z)
    {
        this.arena = arena;
        this.location = new Location(arena.getBukkitWorld(), x, y, z);
        this.type = type;


    }

    public void SpawnItem()
    {
        if (type == ArenaSpawnerType.BRONZE)
        {
            arena.getBukkitWorld().dropItem(location, new ItemStack(Material.BRICK));
        }
        else if (type == ArenaSpawnerType.SILVER)
        {
            arena.getBukkitWorld().dropItem(location, new ItemStack(Material.IRON_INGOT));
        }
        else if (type == ArenaSpawnerType.GOLD)
        {
            arena.getBukkitWorld().dropItem(location, new ItemStack(Material.GOLD_INGOT));
        }
    }
}
