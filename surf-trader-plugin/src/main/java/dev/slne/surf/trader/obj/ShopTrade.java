package dev.slne.surf.trader.obj;

import dev.slne.surf.trader.service.RegistryService;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;

public interface ShopTrade {
  String getID();
  Component getName();
  ObjectList<Object> getPrize();
  ItemStack getItem();
  ShopTradeCurrency getCurrency();

  long getCooldown();
  int getMaximalBuysAtOnce();

  static ShopTrade getTrade(String id) {
    return RegistryService.getInstance().getRegisteredTrades().stream().filter(trade -> trade.getID().equals(id)).findFirst().orElse(null);
  }
}
