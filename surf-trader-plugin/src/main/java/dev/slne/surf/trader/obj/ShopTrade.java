package dev.slne.surf.trader.obj;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;

public interface ShopTrade {
  String getID();
  Component getName();
  Object getPrize();
  ItemStack getItem();
  ShopTradeCurrency getCurrency();

  int getCooldown();
}
