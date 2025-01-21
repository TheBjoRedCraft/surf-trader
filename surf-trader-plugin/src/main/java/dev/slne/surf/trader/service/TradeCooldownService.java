package dev.slne.surf.trader.service;

import dev.slne.surf.trader.SurfTrader;
import dev.slne.surf.trader.obj.ShopTrade;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class TradeCooldownService {
  @Getter
  private static final TradeCooldownService instance = new TradeCooldownService();

  public void setCooldown(Player player, ShopTrade trade) {
    PersistentDataContainer container = player.getPersistentDataContainer();
    NamespacedKey key = this.getTradeKey(trade);

    if(key == null) {
      return;
    }

    container.set(key, PersistentDataType.LONG, System.currentTimeMillis() + trade.getCooldown());
  }

  public void resetCooldown(Player player, ShopTrade trade) {
    PersistentDataContainer container = player.getPersistentDataContainer();
    NamespacedKey key = this.getTradeKey(trade);

    if(key == null) {
      return;
    }

    container.set(key, PersistentDataType.LONG, 0L);
  }

  public long getCooldown(Player player, ShopTrade trade) {
    PersistentDataContainer container = player.getPersistentDataContainer();
    NamespacedKey key = this.getTradeKey(trade);

    if(key == null) {
      return 0;
    }

    Long cooldown = container.get(key, PersistentDataType.LONG);
    return cooldown != null ? cooldown : 0;
  }

  public boolean isCooldown(Player player, ShopTrade trade) {
    PersistentDataContainer container = player.getPersistentDataContainer();
    NamespacedKey key = this.getTradeKey(trade);

    if(key == null) {
      return false;
    }

    Long cooldown = container.get(key, PersistentDataType.LONG);
    return cooldown != null && cooldown > System.currentTimeMillis();
  }

  private NamespacedKey getTradeKey(ShopTrade trade) {
    if(trade == null) {
      return null;
    }

    return new NamespacedKey(SurfTrader.getInstance(), trade.getID());
  }
}
