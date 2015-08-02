package com.minegusta.mgchat;

import com.demigodsrpg.chitchat.Chitchat;
import com.demigodsrpg.chitchat.format.ChatFormat;
import com.demigodsrpg.chitchat.tag.DefaultPlayerTag;
import com.demigodsrpg.chitchat.tag.PlayerTag;
import com.demigodsrpg.chitchat.tag.WorldPlayerTag;
import com.minegusta.mgchat.command.ChatCommand;
import com.minegusta.mgchat.data.ChatGroup;
import com.minegusta.mgchat.listener.ChatGroupListener;
import com.minegusta.mgchat.listener.FactionChatListener;
import com.minegusta.mgchat.listener.WastelandListener;
import com.minegusta.mgchat.tags.FactionTag;
import com.minegusta.mgchat.tags.RaceTag;
import com.minegusta.mgchat.tags.RankTag;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    public static Plugin PLUGIN;

    @Override
    public void onEnable()
    {
        //Set plugin
        PLUGIN = this;

        //Adding tags (priority matters)
        ChatFormat format = Chitchat.getChatFormat();
        format.add(new WorldPlayerTag());

        //Only enable factions tags when factions is enabled.
        if(Bukkit.getPluginManager().isPluginEnabled("Factions"))
        {
            Bukkit.getPluginManager().registerEvents(new FactionChatListener(), this);
            Bukkit.getPluginManager().registerEvents(new ChatGroupListener(), this);
            this.getCommand("chat").setExecutor(new ChatCommand());
            format.add(new FactionTag());
        }

        if(Bukkit.getPluginManager().isPluginEnabled("MGRacesRedone")) format.add(new RaceTag());

        if(Bukkit.getPluginManager().isPluginEnabled("MGApocalypse"))
        {
            //Bukkit.getPluginManager().registerEvents(new WastelandListener(), this);
        }

        for(RankTag tag : RankTag.values()) {

            format.add(tag.getTag());
        }
    }

    @Override
    public void onDisable()
    {
        ChatGroup.clearAlliesChat();
        ChatGroup.clearFactionChat();
    }

}
