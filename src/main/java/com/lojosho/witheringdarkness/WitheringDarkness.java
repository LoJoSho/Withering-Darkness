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
        int tickpercheck = this.getConfig().getInt("TicksPerCheck");
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, this::RunCheck, 0L, tickpercheck);
    }

    public void RunCheck() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            int damageGiven = this.getConfig().getInt("DamageGiven");
            int lightRequired = this.getConfig().getInt("LightLevelRequired");
            Location checkBlock = player.getLocation();
            if (player.getGameMode() == GameMode.SURVIVAL && checkBlock.getBlock().getLightLevel() <= lightRequired) {
                Objects.requireNonNull(player.getPlayer()).damage(damageGiven);
            }
        }
    }
}
