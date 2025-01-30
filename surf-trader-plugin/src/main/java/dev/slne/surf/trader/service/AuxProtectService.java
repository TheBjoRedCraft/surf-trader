package dev.slne.surf.trader.service;

import dev.heliosares.auxprotect.api.AuxProtectAPI;
import dev.heliosares.auxprotect.database.DbEntry;
import dev.heliosares.auxprotect.database.EntryAction;
import dev.heliosares.auxprotect.spigot.AuxProtectSpigot;
import dev.slne.surf.trader.SurfTrader;
import dev.slne.surf.trader.obj.ShopTrade;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.entity.Player;

@Getter
public class AuxProtectService {
  @Getter
  private static final AuxProtectService instance = new AuxProtectService();
  private static final String ACTION_KEY = "trader-trade";
  private EntryAction action = null;

  public void createAction() {
    if (EntryAction.getAction(ACTION_KEY) != null && EntryAction.getAction(ACTION_KEY).exists()) {
      return;
    }

    try {
      action = AuxProtectAPI.createAction(SurfTrader.getInstance().getName(), ACTION_KEY, "bought", null);
    } catch (Exception e) {
      ComponentLogger.logger().error(Component.text("Failed to create action for surf-trade " + e, NamedTextColor.RED));
    }
  }

  public void log(Player player, ShopTrade shopTrade) {
    if(this.action == null) {
      ComponentLogger.logger().error(Component.text("Failed to log shop action for " + player.getName() + " at " + shopTrade.toString(), NamedTextColor.RED));
      return;
    }

    DbEntry dbEntry = new DbEntry(AuxProtectSpigot.getLabel(player),
        action,
        false,
        null,
        AuxProtectSpigot.getLabel(shopTrade),
        "bought" + shopTrade.getItem() + " for " + shopTrade.getPrize()
    );

    AuxProtectAPI.add(dbEntry);
  }
}
