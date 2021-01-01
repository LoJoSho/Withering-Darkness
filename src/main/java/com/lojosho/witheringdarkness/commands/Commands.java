package com.lojosho.witheringdarkness.commands;

import com.lojosho.witheringdarkness.WitheringDarkness;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

    private final WitheringDarkness plugin;

    public Commands(WitheringDarkness plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("wd")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Help1")));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Help2")));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Help3")));
                return true;
            }
            if (args.length <= 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Help1")));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Help2")));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Help3")));
                    return true;
                }
                if (args[0].equalsIgnoreCase("message")) {
                    if (sender.hasPermission("wd.admin")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Damage-Message")));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}