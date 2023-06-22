package com.rydhar.chat.commands;

import com.rydhar.chat.Chat;
import com.rydhar.chat.Constants;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;

import java.util.Collection;

@CommandAlias("clearchat")
@CommandPermission("chat.clearchat")
public class ClearChat extends BaseCommand {
    private Chat core;

    public ClearChat(Chat core) {
        this.core = core;
        core.getCommandManager().registerCommand(this);
    }

    @Default
    @CommandAlias("cc")
    public void clearChat(Player player) {
        Collection<? extends Player> onlinePlayers = core.getServer().getOnlinePlayers();

        for(Player player1 : onlinePlayers) {
            if(!player1.hasPermission("chat.clearchat.bypass")) {
                player1.sendMessage(Component.text(StringUtils.repeat(" \n", 100)));
            }
            player1.sendMessage(Component.text(Constants.MESSAGE_PREFIX + "Chat cleared by " + player.getName()));
        }
    }

    @Subcommand("all")
    @CommandAlias("cca")
    @CommandPermission("chat.clearchat.all")
    public void clearChatAll(Player player) {
        Collection<? extends Player> onlinePlayers = core.getServer().getOnlinePlayers();
        for(Player player1 : onlinePlayers) {
            player1.sendMessage(Component.text(StringUtils.repeat(" \n", 100)));
            player1.sendMessage(Component.text(Constants.MESSAGE_PREFIX + "Chat cleared by " + player.getName()));
        }
    }
}
