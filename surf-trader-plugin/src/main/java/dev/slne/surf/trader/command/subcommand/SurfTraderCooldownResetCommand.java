package dev.slne.surf.trader.command.subcommand;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.slne.surf.trader.command.argument.TradeArgument;
import dev.slne.surf.trader.obj.ShopTrade;
import dev.slne.surf.trader.service.TradeCooldownService;
import dev.slne.surf.trader.util.PluginColor;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class SurfTraderCooldownResetCommand extends CommandAPICommand {
  private final TradeCooldownService tradeCooldownService = TradeCooldownService.getInstance();

  public SurfTraderCooldownResetCommand(String commandName) {
    super(commandName);

    withArguments(new TradeArgument("trade"));
    withOptionalArguments(new PlayerArgument("player"));
    executesPlayer((player, args) -> {
      ShopTrade trade = args.getUnchecked("trade");
      Player target = args.getOrDefaultUnchecked("player", player);

      tradeCooldownService.resetCooldown(target, trade);

      player.sendMessage(Component.text("Du hast den Cooldown für ", PluginColor.LIGHT_BLUE)
          .append(Component.text(target.getName(), PluginColor.GOLD))
          .append(Component.text(" für den Trade ", PluginColor.LIGHT_BLUE))
          .append(trade.getName())
          .append(Component.text(" zurückgesetzt.", PluginColor.LIGHT_BLUE)));
    });
  }
}
