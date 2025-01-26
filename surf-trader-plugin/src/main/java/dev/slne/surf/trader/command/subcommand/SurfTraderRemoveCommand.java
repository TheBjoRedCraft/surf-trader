package dev.slne.surf.trader.command.subcommand;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.WorldArgument;
import dev.slne.surf.trader.command.argument.TraderArgument;

import dev.slne.surf.trader.obj.ShopTrader;
import dev.slne.surf.trader.obj.SpawnableTrader;
import dev.slne.surf.trader.util.PluginColor;
import net.kyori.adventure.text.Component;
import org.bukkit.World;

public class SurfTraderRemoveCommand extends CommandAPICommand {

  public SurfTraderRemoveCommand(String commandName) {
    super(commandName);

    withArguments(new TraderArgument("trader"));
    withOptionalArguments(new WorldArgument("world"));
    executesPlayer((player, args) -> {
      ShopTrader trader = args.getUnchecked("trader");
      World world = args.getOrDefaultUnchecked("world", player.getWorld());

      if(trader instanceof SpawnableTrader spawnableTrader) {
        spawnableTrader.remove(world);
        player.sendMessage(Component.text("Du hast die ", PluginColor.LIGHT_BLUE)
            .append(Component.text(trader.getID().toUpperCase(), PluginColor.GOLD))
            .append(Component.text(" in der Welt ", PluginColor.LIGHT_BLUE))
            .append(Component.text(world.getName(), PluginColor.GOLD))
            .append(Component.text(" entfernt.", PluginColor.LIGHT_BLUE)));
        return;
      }

      player.sendMessage(Component.text("Dieser Händler kann nicht entfernt werden", PluginColor.RED));
    });
  }
}
