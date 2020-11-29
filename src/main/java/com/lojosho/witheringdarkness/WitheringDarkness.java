package com.lojosho.witheringdarkness;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class WitheringDarkness extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void PlayerMovement(PlayerMoveEvent e) {
        if (!e.isCancelled()) {
            if (e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                Location CheckBlock = e.getPlayer().getLocation();
                if (CheckBlock.getBlock().getLightLevel() <= 5) {
                    e.getPlayer().damage(1);
                }
            }
        }
    }
}
