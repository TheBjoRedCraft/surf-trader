package dev.slne.surf.trader.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.slne.surf.trader.command.subcommand.SurfTraderOpenCommand;
import dev.slne.surf.trader.command.subcommand.SurfTraderRemoveCommand;
import dev.slne.surf.trader.command.subcommand.SurfTraderSpawnCommand;

public class SurfTraderCommand extends CommandAPICommand {

  public SurfTraderCommand(String commandName) {
    super(commandName);

    withPermission("surf.trader.command");
    withSubcommand(new SurfTraderOpenCommand("openMenu"));
    withSubcommand(new SurfTraderSpawnCommand("spawnNPC"));
    withSubcommand(new SurfTraderRemoveCommand("removeNPC"));
  }
}
