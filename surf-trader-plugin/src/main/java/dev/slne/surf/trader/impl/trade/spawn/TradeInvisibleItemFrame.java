package dev.slne.surf.trader.impl.trade.spawn;

import dev.slne.surf.trader.annotation.Trade;
import dev.slne.surf.trader.obj.ShopTrade;
import dev.slne.surf.trader.obj.ShopTradeCurrency;
import dev.slne.surf.trader.util.ItemBuilder;

import it.unimi.dsi.fastutil.objects.ObjectList;

import net.kyori.adventure.text.Component;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Trade
public class TradeInvisibleItemFrame implements ShopTrade {
  @Override
  public String getID() {
    return "invisible-item-frame";
  }

  @Override
  public Component getName() {
    return Component.text("Unsichtbarer Item Rahmen");
  }

  @Override
  public ObjectList<Object> getPrize() {
    return ObjectList.of(new ItemBuilder(Material.EMERALD).setAmount(5).build(), new ItemBuilder(Material.ITEM_FRAME).setAmount(20).build());
  }

  @Override
  public ItemStack getItem() {
    return new ItemBuilder(Bukkit.getItemFactory().createItemStack("item_frame[entity_data={id:\"minecraft:item_frame\",Invisible:1b}]")).setAmount(20).build();
  }

  @Override
  public ShopTradeCurrency getCurrency() {
    return ShopTradeCurrency.ITEM;
  }

  @Override
  public long getCooldown() {
    return 72000000L;
  }

  @Override
  public int getMaximalBuysAtOnce() {
    return 32;
  }
}
