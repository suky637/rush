package qc.suky.rush.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArenaTeam {
    @Getter
    @Setter
    private List<Player> players = new ArrayList<>();
    @Getter
    @Setter
    private Location spawn;
    @Getter
    @Setter
    private String teamName;
    @Getter
    @Setter
    private Color color;

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
}
