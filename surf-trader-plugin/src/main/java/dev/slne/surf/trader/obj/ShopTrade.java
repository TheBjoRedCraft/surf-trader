package dev.slne.surf.trader.obj;

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
}
