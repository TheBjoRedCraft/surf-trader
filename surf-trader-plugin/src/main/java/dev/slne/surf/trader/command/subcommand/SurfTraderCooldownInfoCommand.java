package dev.slne.surf.trader.command.subcommand;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;

import dev.slne.surf.trader.command.argument.TradeArgument;
import dev.slne.surf.trader.obj.ShopTrade;
import dev.slne.surf.trader.service.TradeCooldownService;
import dev.slne.surf.trader.util.PluginColor;
import dev.slne.surf.trader.util.TimeUtil;

import net.kyori.adventure.text.Component;

import org.bukkit.entity.Player;

public class SurfTraderCooldownInfoCommand extends CommandAPICommand {
  private final TradeCooldownService tradeCooldownService = TradeCooldownService.getInstance();

  public SurfTraderCooldownInfoCommand(String commandName) {
    super(commandName);

    withArguments(new TradeArgument("trade"));
    withOptionalArguments(new PlayerArgument("player"));
    executesPlayer((player, args) -> {
      ShopTrade trade = args.getUnchecked("trade");
      Player target = args.getOrDefaultUnchecked("player", player);

      player.sendMessage(Component.text("Der Spieler ", PluginColor.LIGHT_BLUE)
          .append(Component.text(target.getName(), PluginColor.GOLD))
          .append(Component.text(" kann den Trade ", PluginColor.LIGHT_BLUE))
          .append(trade.getName())
          .append(Component.text(" wieder in ", PluginColor.LIGHT_BLUE))
          .append(TimeUtil.calcEnding(tradeCooldownService.getCooldown(target, trade), PluginColor.GOLD))
          .append(Component.text(" durchführen.", PluginColor.LIGHT_BLUE))
      );
    });
  }
}
