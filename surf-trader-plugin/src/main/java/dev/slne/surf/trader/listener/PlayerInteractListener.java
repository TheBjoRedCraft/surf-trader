package dev.slne.surf.trader.listener;

import de.oliver.fancynpcs.api.NpcData;
import de.oliver.fancynpcs.api.events.NpcInteractEvent;

import dev.slne.surf.trader.impl.SpawnTrader;
import dev.slne.surf.trader.obj.MenuableTrader;
import dev.slne.surf.trader.obj.ShopTrader;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerInteractListener implements Listener {
  @EventHandler
  public void onNpcInteract(NpcInteractEvent event) {
    NpcData data = event.getNpc().getData();

    if(data.getDisplayName().equalsIgnoreCase(SpawnTrader.STRING_NAME)) {
      ShopTrader spawnTrader = ShopTrader.getTrader(SpawnTrader.STRING_ID);

      if(spawnTrader != null) {
        if(spawnTrader instanceof MenuableTrader trader) {
          trader.openMenu(event.getPlayer());
        }
      }
    }
  }
}
