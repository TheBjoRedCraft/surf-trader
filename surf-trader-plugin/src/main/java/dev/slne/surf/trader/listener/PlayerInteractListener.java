package dev.slne.surf.trader.listener;


import dev.slne.surf.trader.impl.SpawnTrader;
import dev.slne.surf.trader.obj.ShopTrader;
import net.citizensnpcs.api.event.NPCClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerInteractListener implements Listener {
  /*

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

   */

  @EventHandler
  public void onInteract(NPCClickEvent event) {
    Player player = event.getClicker();

    if(event.getNPC().getName().equalsIgnoreCase(SpawnTrader.STRING_NAME)) {
      ShopTrader.asMenuable(ShopTrader.getTrader(SpawnTrader.STRING_ID)).openMenu(player);
    }
  }
}
