package aut.izanamineko.lobbysystem2021.events;

import aut.izanamineko.lobbysystem2021.main;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class GeneralEvent implements Listener {

    static main plugin;

    public GeneralEvent(main instance) {
        plugin = instance;
    }

    public void onDrop(PlayerDropItemEvent dropItem) {
        Player p = dropItem.getPlayer();
        if(p.hasPermission("LobbySystem.DropItem") || this.plugin.getConfig().getString("Config.GeneralEvents.dropItem").equals("false"))
            return;
        dropItem.setCancelled(true);
    }

    public void onPickup(PlayerPickupItemEvent pickupItem) {
        Player p = pickupItem.getPlayer();
        if(p.hasPermission("LobbySystem.PickupItem") || this.plugin.getConfig().getString("Config.GeneralEvents.pickupItem").equals("false"))
            return;
        pickupItem.setCancelled(true);
    }


    public void onEntityDamage(EntityDamageEvent noDamage) {
        if (!(noDamage.getEntity() instanceof Player))
            return;
        if(noDamage.getCause().equals(EntityDamageEvent.DamageCause.FALL) || noDamage.getCause().equals(EntityDamageEvent.DamageCause.DROWNING)
                && ((Player)noDamage.getEntity()).hasPermission("LobbySystem.NoDamage"))
            noDamage.setCancelled(true);
    }
}
