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
        if (label.equalsIgnoreCase("wd toggle")) {
            if (sender.hasPermission("wd.admin")) {
                if (plugin.getConfig().contains("Enabled") == true) {
                    plugin.getConfig().set("Enabled", false);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Toggle-Message-Off")));
                    plugin.reloadConfig();
                    return true;
                } else {
                    plugin.getConfig().set("Enabled", true);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Toggle-Message-On")));
                    plugin.reloadConfig();
                    return true;
                }
            }
        }
        return false;
    }
}
