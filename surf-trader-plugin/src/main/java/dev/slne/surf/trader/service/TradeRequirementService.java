package dev.slne.surf.trader.service;

import dev.slne.surf.trader.obj.ShopTrade;
import dev.slne.surf.trader.obj.ShopTradeCurrency;
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

    Object required = trade.getPrize();

    if(required instanceof ItemStack stack) {
      return player.getInventory().containsAtLeast(stack, stack.getAmount());
    } else if(required instanceof Integer amount) {
      if(trade.getCurrency().equals(ShopTradeCurrency.CAST_COINS)) {
        /* Add Transaction API for CastCoins */
      } else if(trade.getCurrency().equals(ShopTradeCurrency.EVENT_COINS)) {
        /* Add Transaction API for EventCoins */
      }

      return true;
    }

    return false;
  }

  public void removeRequirements(Player player, ShopTrade trade) {
    if(trade == null) {
      return;
    }

    Object required = trade.getPrize();

    if(required instanceof ItemStack stack) {
      player.getInventory().removeItem(stack);
    } else if(required instanceof Integer amount) {
      if(trade.getCurrency().equals(ShopTradeCurrency.CAST_COINS)) {
        /* Add Transaction API for CastCoins */
      } else if(trade.getCurrency().equals(ShopTradeCurrency.EVENT_COINS)) {
        /* Add Transaction API for EventCoins */
      }
    }
  }
}