package qc.suky.rush.objects;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArenaTeam {
    private List<Player> players = new ArrayList<>();
    private Location spawn;
    private String teamName;

    public boolean isAllTeamDead()
    {
        boolean isDead = true;
        for (Player plr : players)
        {
            if (!(plr.getGameMode().equals(GameMode.SPECTATOR)))
            {
                isDead = false;
            }
        }
        return isDead;
    }

    public String getTeamName()
    {
        return teamName;
    }
}
