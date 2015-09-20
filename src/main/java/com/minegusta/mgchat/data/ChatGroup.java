package com.minegusta.mgchat.data;

import com.google.common.collect.Lists;

import java.util.ArrayList;

public class ChatGroup {

    private static ArrayList<String> faction = Lists.newArrayList();
    private static ArrayList<String> allies = Lists.newArrayList();

    public static ArrayList<String> getFaction() {
        return faction;
    }

    public static void setFactionChat(String name) {
        faction.add(name);
    }

    public static void removeFactionChat(String name) {
        faction.remove(name);
    }

    public static void clearFactionChat() {
        faction.clear();
    }

    public static boolean factionGroupContains(String name) {
        return faction.contains(name);
    }

    public static ArrayList<String> getAllies() {
        return allies;
    }

    public static void setAlliesChat(String name) {
        allies.add(name);
    }

    public static void removeAlliesChat(String name) {
        allies.remove(name);
    }

    public static void clearAlliesChat() {
        allies.clear();
    }

    public static boolean alliedGroupContains(String name) {
        return allies.contains(name);
    }
}
