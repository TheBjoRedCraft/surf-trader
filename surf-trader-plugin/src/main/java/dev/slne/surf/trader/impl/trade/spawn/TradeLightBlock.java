package dev.slne.surf.trader.impl.trade.spawn;

import dev.slne.surf.trader.annotation.Trade;
import dev.slne.surf.trader.obj.ShopTrade;
import dev.slne.surf.trader.obj.ShopTradeCurrency;
import dev.slne.surf.trader.util.ItemBuilder;

import it.unimi.dsi.fastutil.objects.ObjectList;

import net.kyori.adventure.text.Component;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Trade
public class TradeLightBlock implements ShopTrade {
  @Override
  public String getID() {
    return "light-block";
  }

  @Override
  public Component getName() {
    return Component.text("Unsichtbarer Licht Block");
  }

  @Override
  public ObjectList<Object> getPrize() {
    return ObjectList.of(new ItemBuilder(Material.EMERALD).setAmount(5).build(), new ItemBuilder(Material.REDSTONE_LAMP).setAmount(20).build());
  }

  @Override
  public ItemStack getItem() {
    return new ItemBuilder(Material.LIGHT).setAmount(20).build();
  }

  @Override
  public ShopTradeCurrency getCurrency() {
    return ShopTradeCurrency.ITEM;
  }

  @Override
  public long getCooldown() {
    return 75600000L;
  }

  @Override
  public int getMaximalBuysAtOnce() {
    return 32;
  }
}
