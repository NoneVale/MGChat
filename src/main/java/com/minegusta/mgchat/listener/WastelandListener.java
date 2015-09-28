package com.minegusta.mgchat.listener;

import com.demigodsrpg.chitchat.Chitchat;
import com.demigodsrpg.chitchat.tag.ChatScope;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class WastelandListener implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onChat(AsyncPlayerChatEvent e)
    {
        Player s = e.getPlayer();
        String message = e.getMessage();
        
        if(message.startsWith("/") || e.getPlayer().hasPermission("wasteland.farchat"))return;
        
        e.setCancelled(true);

        BaseComponent send = Chitchat.getChatFormat().getFormattedMessage(s, ChatScope.LOCAL, message);
        Bukkit.getOnlinePlayers().stream().filter(p -> p.getLocation().distance(e.getPlayer().getLocation()) < 900 || p.hasPermission("wasteland.farchat")).forEach(p -> p.sendMessage(send.toString()));
        Bukkit.getLogger().info(send.toString());
    }
}
