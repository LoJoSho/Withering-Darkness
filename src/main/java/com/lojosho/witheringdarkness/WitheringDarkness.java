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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;

// I really need to go back to optimize a few things... oh god have mercy on my soul

public final class WitheringDarkness extends JavaPlugin {

    int damageGiven = this.getConfig().getInt("DamageGiven");
    List<String> disabledWorlds = this.getConfig().getStringList("Disabled-worlds");
    String damageMessage = this.getConfig().getString("Damage-Message");
    int tickPerCheck = this.getConfig().getInt("TicksPerCheck");
    int ticksPerMessage = this.getConfig().getInt("TicksPerMessage");
    int blockLightRequired = this.getConfig().getInt("BlockLightRequired");
    int skyLightRequired = this.getConfig().getInt("SkyLightRequired");
    String potionAppplied = this.getConfig().getString("TypeofEffect");
    int potionDuration = this.getConfig().getInt("EffectDuration");
    int potionAmplifier = this.getConfig().getInt("EffectAmplifier");

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        registerCommands();
        if (this.getConfig().getBoolean("Enabled")) {
            BukkitScheduler scheduler = getServer().getScheduler();
            scheduler.scheduleSyncRepeatingTask(this, this::lightCheck, 0L, tickPerCheck);
            scheduler.scheduleSyncRepeatingTask(this, this::runMessage, 0L, ticksPerMessage);
        }
        if (this.getConfig().getBoolean("bstats")) {
            int pluginId = 9552; // <-- Replace with the id of your plugin!
            Metrics metrics = new Metrics(this, pluginId);
        }
    }

    

    public void registerCommands() {
        getCommand("wd").setExecutor(new ToggleCommand(this));
        getCommand("wd").setExecutor(new HelpCommand(this));
        getCommand("wd").setExecutor(new MessageTest(this));
    }

    public void lightCheck() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Location checkBlock = player.getLocation();
            if (player.getGameMode() == GameMode.SURVIVAL) {
                {
                    if (!disabledWorlds.contains(player.getWorld().getName()) && !player.hasPermission("wd.immune")) {
                        if (checkBlock.getBlock().getLightLevel() <= blockLightRequired) {
                            player.damage(damageGiven);
                            if (this.getConfig().getBoolean("PotionsEnabled")) {
                                PotionEffectType potionEffects = PotionEffectType.getByName(potionAppplied);
                                player.addPotionEffect(new PotionEffect(potionEffects, potionDuration, potionAmplifier));
                            }
                        } else {
                            if (checkBlock.getBlock().getLightLevel() <= skyLightRequired) {
                                player.damage(damageGiven);
                                if (this.getConfig().getBoolean("PotionsEnabled")) {
                                    PotionEffectType potionEffects = PotionEffectType.getByName(potionAppplied);
                                    player.addPotionEffect(new PotionEffect(potionEffects, potionDuration, potionAmplifier));
                                }
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
                    if (!disabledWorlds.contains(player.getWorld().getName()) && !player.hasPermission("wd.immune")) {
                        if (checkBlock.getBlock().getLightLevel() <= blockLightRequired) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', damageMessage));
                        } else {
                            if (checkBlock.getBlock().getLightLevel() <= skyLightRequired) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', damageMessage));
                            }
                        }
                    }
                }
            }
        }
    }
}