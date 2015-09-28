package com.minegusta.mgchat.listener;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.cmd.arg.ARFaction;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.minegusta.mgchat.data.ChatGroup;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by NoneVale on 8/2/2015.
 */
public class ChatGroupListener implements Listener
{

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        MPlayer uplayer = MPlayer.get(player);
        Faction faction = uplayer.getFaction();
        String factionName = faction.getName();
        String message = event.getMessage();
        if (!ChatGroup.alliedGroupContains(player.getName()) && !ChatGroup.factionGroupContains(player.getName())) {
            return;
        }
        event.setCancelled(true);
        event.getRecipients().clear();
        if (factionName.contains("Wilderness")) return;
        if (ChatGroup.alliedGroupContains(player.getName())) {
            if (factionName.contains("Wilderness")) {
                ChatGroup.removeAlliesChat(player.getName());
            }
            String prefix = ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + "AC" + ChatColor.DARK_PURPLE + "] ";
            String name = ChatColor.BOLD + player.getName()+ ": ";
            String sendMessage = ChatColor.LIGHT_PURPLE + message.substring(0, message.length());
            String fname = ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + factionName + ChatColor.DARK_PURPLE + "] ";

            for(String s : faction.getRelationWishes().keySet())
            {
                Rel wish = faction.getRelationTo(ARFaction.get().read(s).getResult());
                if(wish != null && wish == Rel.ALLY)
                {
                    Faction ally = ARFaction.get().read(s).getResult();
                    for(Player allyPlayer : ally.getOnlinePlayers())
                    {
                        allyPlayer.sendMessage(prefix + fname + name + sendMessage);
                    }
                }
            }
            for(Player players : faction.getOnlinePlayers())
            {
                players.sendMessage(prefix + fname + name + sendMessage);
            }
        }
        else if (ChatGroup.factionGroupContains(player.getName())) {
            if (factionName.equalsIgnoreCase("Wilderness")) {
                ChatGroup.removeFactionChat(player.getName());
            }

            String send = ChatColor.GREEN + "[FC] " + ChatColor.BOLD + player.getName()+ ": " + ChatColor.DARK_GREEN + message.substring(0, message.length());
            for(Player players : faction.getOnlinePlayers())
            {
                players.sendMessage(send);
            }
        }
    }
}
