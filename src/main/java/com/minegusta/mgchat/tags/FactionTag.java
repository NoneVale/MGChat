package com.minegusta.mgchat.tags;


import com.demigodsrpg.chitchat.tag.ChatScope;
import com.demigodsrpg.chitchat.tag.PlayerTag;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FactionTag extends PlayerTag {
    @Override
    public String getName() {
        return "faction";
    }

    @Override
    public String getFor(Player player) {
        MPlayer uplayer = MPlayer.get(player);
        Faction faction = uplayer.getFaction();
        String factionName = faction.getName();
        if(factionName.contains("Wilderness")) factionName = "";
        else factionName = "[" +factionName + "]";
        return factionName;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public ChatScope getScope()
    {
        return ChatScope.LOCAL;
    }
}
