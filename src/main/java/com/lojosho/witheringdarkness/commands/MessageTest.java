package com.lojosho.witheringdarkness.commands;

import com.lojosho.witheringdarkness.WitheringDarkness;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class MessageTest implements CommandExecutor {

    private final WitheringDarkness plugin;
    public MessageTest(WitheringDarkness plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("wd")) {
            if (args[0].equalsIgnoreCase("message")) {
                if (sender.hasPermission("wd.admin")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Damage-Message"))));
                    return true;
                }
            }
        }
        return false;
    }
}
