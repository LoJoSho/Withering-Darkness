package com.lojosho.witheringdarkness;

import com.lojosho.witheringdarkness.commands.HelpCommand;
import com.lojosho.witheringdarkness.commands.MessageTest;
import com.lojosho.witheringdarkness.commands.ToggleCommand;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;
import java.util.Objects;

public final class WitheringDarkness extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        int tickpercheck = this.getConfig().getInt("TicksPerCheck");
        int tickspermessage = this.getConfig().getInt("TicksPerMessage");
        if (this.getConfig().getBoolean("Enabled")) {
            BukkitScheduler scheduler = getServer().getScheduler();
            scheduler.scheduleSyncRepeatingTask(this, this::RunCheck, 0L, tickpercheck);
            scheduler.scheduleSyncRepeatingTask(this, this::runMessage, 0L, tickspermessage);
        }
        if (Bukkit.getVersion().contains("1.13")) {
            getLogger().info("1.13 is an experimental version. It has a high chance to work, however, isn't 100%.");
        }
        if (Bukkit.getVersion().contains("1.14")) {
            getLogger().info("1.14 is an experimental version. It has a high chance to work, however, isn't 100%.");
        }
        if (Bukkit.getVersion().contains("1.15")) {
            getLogger().info("1.15 is an experimental version. It has a high chance to work, however, isn't 100%.");
        }
        if (this.getConfig().getBoolean("bstats")) {
            int pluginId = 9552; // <-- Replace with the id of your plugin!
            Metrics metrics = new Metrics(this, pluginId);
        }
        registerCommands();
    }

    int damageGiven = this.getConfig().getInt("DamageGiven");
    int BlockLightRequired = this.getConfig().getInt("BlockLightRequired");
    int SkyLightRequired = this.getConfig().getInt("SkyLightRequired");
    List<String> DisabledWorlds = this.getConfig().getStringList("Disabled-worlds");
    String damageMessage = this.getConfig().getString("Damage-Message");

    public void RunCheck() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Location checkBlock = player.getLocation();
            if (player.getGameMode() == GameMode.SURVIVAL) {
                {
                    if (!DisabledWorlds.contains(player.getWorld().getName()) && !player.hasPermission("wd.immune")) {
                        if (checkBlock.getBlock().getLightLevel() <= BlockLightRequired) {
                            player.damage(damageGiven);
                        } else {
                            if (checkBlock.getBlock().getLightLevel() <= SkyLightRequired) {
                                player.damage(damageGiven);
                            }
                        }
                    }
                }
            }
        }
    }

    public void runMessage() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Location checkBlock = player.getLocation();
            if (player.getGameMode() == GameMode.SURVIVAL) {
                {
                    if (!DisabledWorlds.contains(player.getWorld().getName()) && !player.hasPermission("wd.immune")) {
                        if (checkBlock.getBlock().getLightLevel() <= BlockLightRequired) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', damageMessage));
                        } else {
                            if (checkBlock.getBlock().getLightLevel() <= SkyLightRequired) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', damageMessage));
                            }
                        }
                    }
                }
            }
        }
    }

    public void registerCommands() {
        Objects.requireNonNull(getCommand("toggle")).setExecutor(new ToggleCommand(this));
        Objects.requireNonNull(getCommand("messageTest")).setExecutor(new MessageTest(this));
        Objects.requireNonNull(getCommand("HelpCommand")).setExecutor(new HelpCommand(this));
    }
}