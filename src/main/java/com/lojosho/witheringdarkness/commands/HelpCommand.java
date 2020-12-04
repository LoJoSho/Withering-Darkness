package com.lojosho.witheringdarkness.commands;

import com.lojosho.witheringdarkness.WitheringDarkness;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {

    private final WitheringDarkness plugin;
    public  HelpCommand(WitheringDarkness plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("wd")) {
            sender.sendMessage("Withering Darkness Helpful Commands");
            sender.sendMessage("/wd toggle - Toggles the plugin, requires a restart");
            sender.sendMessage("/wd message - View the damage message");
            sender.sendMessage("");
        }
        return false;
    }
}
