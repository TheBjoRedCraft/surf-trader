package dev.slne.surf.trader.command.subcommand;

import dev.jorel.commandapi.CommandAPICommand;
import dev.slne.surf.trader.command.argument.TraderArgument;
import dev.slne.surf.trader.obj.MenuableTrader;
import dev.slne.surf.trader.obj.ShopTrader;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class SurfTraderOpenCommand extends CommandAPICommand {

  public SurfTraderOpenCommand(String commandName) {
    super(commandName);

    withPermission("surf.trader.command.open");
    withArguments(new TraderArgument("trader"));
    executesPlayer((player, args) -> {
      ShopTrader trader = args.getUnchecked("trader");

      if(trader instanceof MenuableTrader menuableTrader) {
        menuableTrader.openMenu(player);
        return;
      }

      player.sendMessage(Component.text("Dieser Händler hat kein Menü.", NamedTextColor.RED));
    });
  }
}
