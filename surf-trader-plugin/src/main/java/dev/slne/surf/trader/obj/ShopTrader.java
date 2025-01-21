package dev.slne.surf.trader.obj;

import dev.slne.surf.trader.service.RegistryService;
import it.unimi.dsi.fastutil.objects.ObjectSet;

public interface ShopTrader {
  String getID();

  ObjectSet<ShopTrade> getTrades();
  void addTrade(ShopTrade trade);
  void removeTrade(ShopTrade trade);


  static ShopTrader getTrader(String id) {
    return RegistryService.getInstance().getRegisteredTraders().stream().filter(trader -> trader.getID().equals(id)).findFirst().orElse(null);
  }
}
