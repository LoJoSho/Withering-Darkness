package com.lojosho.witheringdarkness.commands;

import com.lojosho.witheringdarkness.WitheringDarkness;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {

    private final WitheringDarkness plugin;

    public HelpCommand(WitheringDarkness plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("wd")) {
            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage("Withering Darkness Helpful Commands");
                sender.sendMessage("/wd message - View the damage message");
                sender.sendMessage("/wd reload - reloads the plugin");
            }
        }
        return false;
    }
}
