package com.lojosho.witheringdarkness;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Objects;

public final class WitheringDarkness extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        //getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, this::RunCheck, 0L, 20L);
    }
    public void RunCheck() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                Location checkBlock = player.getLocation();
                int lightRequired = this.getConfig().getInt("LightLevelRequired");
                if (checkBlock.getBlock().getLightLevel() <= lightRequired) {
                    int damageGiven = this.getConfig().getInt("DamageGiven");
                    Objects.requireNonNull(player.getPlayer()).damage(damageGiven);
                }
            }
        }
    }
}
