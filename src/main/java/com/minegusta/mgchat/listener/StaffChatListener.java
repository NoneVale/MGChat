package com.minegusta.mgchat.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * NoneVale
 * 7/5/2015
 * MGChat
 * com.minegusta.mgchat.listener
 */
public class StaffChatListener implements Listener
{

    @EventHandler
    public void onSCE(AsyncPlayerChatEvent event)
    {
        String msg = event.getMessage();
        Player player = event.getPlayer();
        String starter = "";
        String staff = "";
        String name = "";
        String message = "";
        if (!msg.startsWith("*"))return;
        event.getRecipients().clear();
        event.setCancelled(true);
        if (msg.startsWith("*"))
        {
            if (!player.hasPermission("mg.staff"))
            {
                event.getRecipients().clear();
                event.setCancelled(true);
            }
            else if (player.hasPermission("mg.staff") || player.isOp())
            {
                starter = ChatColor.DARK_GRAY + "[" + ChatColor.LIGHT_PURPLE + "MG" + ChatColor.DARK_GRAY + "]";
                staff = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "STAFF" + ChatColor.DARK_GRAY + "]";
                name = ChatColor.GRAY + "" + ChatColor.BOLD + player.getName() + ChatColor.DARK_GRAY + ": ";
                message = ChatColor.YELLOW + msg.substring(1, msg.length());
            }
        }
        for (Player players : Bukkit.getOnlinePlayers())
        {
            if (!players.hasPermission("mg.staff") || !players.isOp())
            {
                event.getRecipients().clear();
                event.setCancelled(true);
            }
            else if (players.hasPermission("mg.staff") || players.isOp())
            {
                players.sendMessage(starter + staff + name + message);
                players.playSound(players.getLocation(), Sound.NOTE_PIANO, 1f, 1f);
            }
        }
    }
}
