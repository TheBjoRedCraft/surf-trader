package dev.slne.surf.trader.command.subcommand;

import dev.jorel.commandapi.CommandAPICommand;

public class SurfTraderCooldownCommand extends CommandAPICommand {
  public SurfTraderCooldownCommand(String commandName) {
    super(commandName);

    withSubcommand(new SurfTraderCooldownResetCommand("reset"));
    withSubcommand(new SurfTraderCooldownInfoCommand("info"));
  }
}
