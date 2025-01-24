package dev.slne.surf.trader.service;

import dev.slne.surf.trader.obj.ShopTrade;
import dev.slne.surf.trader.obj.ShopTradeCurrency;
import it.unimi.dsi.fastutil.objects.ObjectList;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TradeRequirementService {
  @Getter
  private static final TradeRequirementService instance = new TradeRequirementService();

  public boolean hasRequirements(Player player, ShopTrade trade) {
    if(trade == null) {
      return false;
    }

    ObjectList<Object> required = trade.getPrize();
    boolean hasRequirements = false;

    for (Object object : required) {
      if(object instanceof ItemStack stack) {
        hasRequirements = player.getInventory().containsAtLeast(stack, stack.getAmount());
      } else if(object instanceof Integer amount) {
        if(trade.getCurrency().equals(ShopTradeCurrency.CAST_COINS)) {
          /* Add Transaction API for CastCoins */
        } else if(trade.getCurrency().equals(ShopTradeCurrency.EVENT_COINS)) {
          /* Add Transaction API for EventCoins */
        }

        return hasRequirements;
      }
    }

    return hasRequirements;
  }

  public void removeRequirements(Player player, ShopTrade trade) {
    if(trade == null) {
      return;
    }

    ObjectList<Object> required = trade.getPrize();

    for (Object object : required) {
      if(object instanceof ItemStack stack) {
        player.getInventory().removeItem(stack);
      } else if(object instanceof Integer amount) {
        if(trade.getCurrency().equals(ShopTradeCurrency.CAST_COINS)) {
          /* Add Transaction API for CastCoins */
        } else if(trade.getCurrency().equals(ShopTradeCurrency.EVENT_COINS)) {
          /* Add Transaction API for EventCoins */
        }
      }
    }
  }
}