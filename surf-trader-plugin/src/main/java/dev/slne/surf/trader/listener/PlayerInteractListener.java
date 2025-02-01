package dev.slne.surf.trader.listener;


import dev.slne.surf.trader.SurfTrader;
import dev.slne.surf.trader.impl.SpawnTrader;
import dev.slne.surf.trader.obj.ShopTrader;

import lol.pyr.znpcsplus.api.entity.EntityProperty;
import lol.pyr.znpcsplus.api.event.NpcInteractEvent;
import lol.pyr.znpcsplus.api.npc.Npc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerInteractListener implements Listener {
  @EventHandler
  public void onInteract(NpcInteractEvent event) {
    Player player = event.getPlayer();
    Npc npc = event.getNpc();

    EntityProperty<?> property = SurfTrader.getInstance().getTraderProperty();

    if(property == null) {
      return;
    }

    if(!npc.hasProperty(property)) {
      return;
    }


    if(npc.getProperty(property).equals(SpawnTrader.STRING_ID)) {
      new BukkitRunnable() {
        @Override
        public void run() {
          ShopTrader.asMenuable(ShopTrader.getTrader(SpawnTrader.STRING_ID)).openMenu(player);
        }
      }.runTaskLater(SurfTrader.getInstance(), 1L);
    }
  }
}
