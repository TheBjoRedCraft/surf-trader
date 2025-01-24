package dev.slne.surf.trader.listener;


import dev.slne.surf.trader.impl.SpawnTrader;
import dev.slne.surf.trader.obj.ShopTrader;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerInteractListener implements Listener {

  @EventHandler
  public void onInteract(PlayerInteractAtEntityEvent event) {
    Player player = event.getPlayer();

    if(event.getRightClicked().getScoreboardTags().contains(SpawnTrader.STRING_ID)) {
      ShopTrader.asMenuable(ShopTrader.getTrader(SpawnTrader.STRING_ID)).openMenu(player);
    }
  }
}
