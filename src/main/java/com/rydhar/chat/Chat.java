package com.rydhar.chat;

import com.rydhar.chat.commands.ClearChat;
import com.rydhar.chat.events.PlayerEventHandler;
import co.aikar.commands.PaperCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.luckperms.api.LuckPerms;

import java.util.logging.Level;

public final class Chat extends JavaPlugin {
    public LuckPerms luckPerms;

    private PaperCommandManager commandManager;

    // Plugin statup logic
    @Override
    public void onEnable() {
        setupACF();
        loadModules();

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider == null) {
            getLogger().log(Level.SEVERE, "LuckPerms not found. Disabling Chat");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        luckPerms = provider.getProvider();

        getLogger().info("Enabled!");
    }

    // Plugin shutdown logic
    @Override
    public void onDisable() {
        getLogger().info("Shutting down!");
    }

    private void setupACF() {
        commandManager = new PaperCommandManager(this);
    }

    // Adding command/events to main
    private void loadModules() {
        new PlayerEventHandler(this);
        new ClearChat(this);
    }

    public PaperCommandManager getCommandManager() {
        return commandManager;
    }
}
