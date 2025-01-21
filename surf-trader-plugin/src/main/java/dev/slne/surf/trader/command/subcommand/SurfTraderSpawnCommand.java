package dev.slne.surf.trader.command.subcommand;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.LocationArgument;
import dev.slne.surf.trader.command.argument.TraderArgument;
import dev.slne.surf.trader.obj.ShopTrader;
import dev.slne.surf.trader.obj.SpawnableTrader;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;

public class SurfTraderSpawnCommand extends CommandAPICommand {

  public SurfTraderSpawnCommand(String commandName) {
    super(commandName);

    withArguments(new TraderArgument("trader"));
    withOptionalArguments(new LocationArgument("location"));
    executesPlayer((player, args) -> {
      ShopTrader trader = args.getUnchecked("trader");
      Location location = args.getOrDefaultUnchecked("location", player.getLocation());

      if(trader instanceof SpawnableTrader spawnableTrader) {
        spawnableTrader.spawn(location);
        return;
      }

      player.sendMessage(Component.text("Dieser Händler kann nicht gespawnt werden", NamedTextColor.RED));
    });
  }
}
