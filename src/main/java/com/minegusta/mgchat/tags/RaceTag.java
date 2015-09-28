package com.minegusta.mgchat.tags;

import com.demigodsrpg.chitchat.tag.ChatScope;
import com.demigodsrpg.chitchat.tag.PlayerTag;
import com.minegusta.mgracesredone.main.Races;
import org.bukkit.entity.Player;

public class RaceTag extends PlayerTag {

    @Override
    public String getName() {
        return "race";
    }

    private static final String[] uuids = new String[]{"d5133464-b1ef-42b4-9ad4-8cac217d40f0", "1fb8eb3a-5431-490a-aef4-4e544c2994be"};

    @Override
    public String getFor(Player player)
    {
        for(String s : uuids)
        {
            if(s.equals(player.getUniqueId().toString()))
            {
                return "";
            }
        }
        return Races.getRace(player).getTag();
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public ChatScope getScope()
    {
        return ChatScope.LOCAL;
    }
}
