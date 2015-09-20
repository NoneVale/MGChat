package com.minegusta.mgchat.command;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.minegusta.mgchat.data.ChatGroup;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this!");
            return true;
        }
        Player player = (Player) sender;
        MPlayer uplayer = MPlayer.get(player);
        Faction faction = uplayer.getFaction();
        String factionName = faction.getName();
        if(factionName.contains("Wilderness")) {
            player.sendMessage(ChatColor.RED + "You must be in a faction to use this!");
            return true;
        }
        if (args.length == 0) {
            player.sendMessage(ChatColor.YELLOW + "ChatGroups: " + ChatColor.GRAY + "Faction(F), Allies(A), Global(G)");
            return true;
        }
        else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("faction") || args[0].equalsIgnoreCase("f")) {
                if (ChatGroup.alliedGroupContains(player.getName())) {
                    ChatGroup.removeAlliesChat(player.getName());
                }
                ChatGroup.setFactionChat(player.getName());
                player.sendMessage(ChatColor.YELLOW + "You have set your chat group to " + ChatColor.GREEN + "Factions");
                return true;
            }
            else  if (args[0].equalsIgnoreCase("allies") || args[0].equalsIgnoreCase("a")) {
                if (ChatGroup.factionGroupContains(player.getName())) {
                    ChatGroup.removeFactionChat(player.getName());
                }
                ChatGroup.setAlliesChat(player.getName());
                player.sendMessage(ChatColor.YELLOW + "You have set your chat group to " + ChatColor.LIGHT_PURPLE + "Allies");
                return true;
            }
            else  if (args[0].equalsIgnoreCase("global") || args[0].equalsIgnoreCase("g")) {
                if (ChatGroup.alliedGroupContains(player.getName())) {
                    ChatGroup.removeAlliesChat(player.getName());
                }
                if (ChatGroup.factionGroupContains(player.getName())) {
                    ChatGroup.removeFactionChat(player.getName());
                }
                player.sendMessage(ChatColor.YELLOW + "You have set your chat group to " + ChatColor.GRAY + "Global");
                return true;
            }
            else {
                player.sendMessage(ChatColor.RED + "That is not a valid chat group!");
                return true;
            }
        }
        else if (args.length > 1) {
            player.sendMessage(ChatColor.RED + "There was a syntax error, please try again.");
            return true;
        }
        return true;
    }
}
