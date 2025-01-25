package dev.slne.surf.trader.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import dev.slne.surf.trader.obj.ShopTrade;

import dev.slne.surf.trader.util.TimeUtil;
import java.time.Duration;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public class TradeService {
  @Getter
  private static final TradeService instance = new TradeService();
  private final TradeCooldownService cooldownService = TradeCooldownService.getInstance();
  private final TradeRequirementService requirementService = TradeRequirementService.getInstance();

  private final LoadingCache<ItemStack, ShopTrade> tradeCache = Caffeine
      .newBuilder()
      .expireAfterWrite(Duration.ofMinutes(30))
      .build(this::loadTrade);

  public void buy(Player player, ShopTrade trade) {
    if (cooldownService.isCooldown(player, trade)) {
      player.sendActionBar(Component.text("Bitte warte noch ", NamedTextColor.GRAY).append(TimeUtil.calcEnding(cooldownService.getCooldown(player, trade), NamedTextColor.GOLD)));
      return;
    }

    if(!requirementService.hasRequirements(player, trade)) {
      return;
    }

    requirementService.removeRequirements(player, trade);

    if (player.getInventory().firstEmpty() == -1) {
      player.getWorld().dropItem(player.getLocation(), trade.getItem()).setOwner(player.getUniqueId());
    } else {
      player.getInventory().addItem(trade.getItem());
    }

    cooldownService.setCooldown(player, trade);

    player.sendMessage(Component.text("Du hast ", NamedTextColor.GOLD).append(trade.getName()).append(Component.text(" gekauft", NamedTextColor.GOLD)));
  }

  public void buyAYC(Player player, ShopTrade trade) {
    if (cooldownService.isCooldown(player, trade)) {
      player.sendActionBar(Component.text("Bitte warte noch ", NamedTextColor.GRAY).append(TimeUtil.calcEnding(cooldownService.getCooldown(player, trade), NamedTextColor.GOLD)));
      return;
    }

    int count = 0;

    while (requirementService.hasRequirements(player, trade) && !cooldownService.isCooldown(player, trade)) {
      if(count >= trade.getMaximalBuysAtOnce()) {
        break;
      }

      requirementService.removeRequirements(player, trade);

      if (player.getInventory().firstEmpty() == -1) {
        player.getWorld().dropItem(player.getLocation(), trade.getItem()).setOwner(player.getUniqueId());
      } else {
        player.getInventory().addItem(trade.getItem());
      }

      count++;
    }

    if (count > 0) {
      cooldownService.setCooldown(player, trade);
      player.sendMessage(Component.text("Du hast ", NamedTextColor.GOLD).append(Component.text(count)).append(Component.text("x ")).append(trade.getName()).append(Component.text(" gekauft", NamedTextColor.GOLD)));
    } else {
      player.sendMessage(Component.text("Du hast nicht genug Ressourcen, um diesen Handel durchzuführen.", NamedTextColor.RED));
    }
  }

  public ShopTrade getTrade(ItemStack stack) {
    return tradeCache.get(stack);
  }

  private ShopTrade loadTrade(ItemStack stack) {
    for (ShopTrade registeredTrade : RegistryService.getInstance().getRegisteredTrades()) {
      if (registeredTrade.getItem().isSimilar(stack)) {
        return registeredTrade;
      }
    }

    return null;
  }
}
