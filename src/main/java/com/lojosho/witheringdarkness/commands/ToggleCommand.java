package com.lojosho.witheringdarkness.commands;

import com.lojosho.witheringdarkness.WitheringDarkness;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleCommand implements CommandExecutor {

    private final WitheringDarkness plugin;

    public ToggleCommand(WitheringDarkness plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String toggleon = plugin.getConfig().getString("Toggle-Message-On");
        String toggleoff = plugin.getConfig().getString("Toggle-Message-Off");
        if (label.equalsIgnoreCase("wd")) {
            if (args[0].equalsIgnoreCase("toggle")) {
                if (sender.hasPermission("wd.admin")) {
                    if (plugin.getConfig().contains("Enabled")) {
                        plugin.getConfig().set("Enabled", false);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(toggleoff)));
                    } else {
                        plugin.getConfig().set("Enabled", true);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(toggleon)));
                    }
                    plugin.reloadConfig();
                    return true;
                }
            }
        }
        return false;
    }
}
