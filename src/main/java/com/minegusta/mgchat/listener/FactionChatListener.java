package com.minegusta.mgchat.listener;


import com.massivecraft.factions.Rel;
import com.massivecraft.factions.cmd.arg.ARFaction;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.util.Txt;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class FactionChatListener implements Listener
{
    @EventHandler(priority = EventPriority.LOW)
    public void onEvent(AsyncPlayerChatEvent e)
    {
        String message = e.getMessage();
        if(!message.startsWith("!"))
        {
            return;
        }

        e.getRecipients().clear();
        e.setCancelled(true);

        Player p = e.getPlayer();
        MPlayer uplayer = MPlayer.get(p);
        Faction faction = uplayer.getFaction();
        String factionName = faction.getName();
        String role = Txt.upperCaseFirst(uplayer.getRole().toString().toLowerCase());
        if(factionName.contains("Wilderness")) return;

        if(message.startsWith("!!"))
        {
            String prefix = ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + "AC" + ChatColor.DARK_PURPLE + "] ";
            String name = ChatColor.BOLD + p.getName()+ ": ";
            String sendMessage = ChatColor.LIGHT_PURPLE + message.substring(2, message.length());
            String fname = ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + factionName + ChatColor.DARK_PURPLE + "] ";

            for(String s : faction.getRelationWishes().keySet())
            {
                Rel wish = faction.getRelationTo(ARFaction.get().read(s).getResult());
                if(wish != null && wish == Rel.ALLY)
                {
                    Faction ally = ARFaction.get().read(s).getResult();
                    for(Player allyPlayer : ally.getOnlinePlayers())
                    {
                        allyPlayer.sendMessage(prefix + fname + "[" + role +  "]" + name + sendMessage);
                    }
                }
            }
            for(Player player : faction.getOnlinePlayers())
            {
                player.sendMessage(prefix + fname +  "[" + role +  "]" + name + sendMessage);
            }
        }
        else
        {
            String send = ChatColor.GREEN + "[FC] " + ChatColor.BOLD + p.getName()+ "[" + role +  "]"  + ":"  + ChatColor.DARK_GREEN + message.substring(1, message.length());
            for(Player player : faction.getOnlinePlayers())
            {
                player.sendMessage(send);
            }
        }
    }
}
