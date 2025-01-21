package dev.slne.surf.trader.command.argument;

import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.CustomArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.slne.surf.trader.obj.ShopTrader;
import dev.slne.surf.trader.service.RegistryService;

public class TraderArgument extends CustomArgument<ShopTrader, String> {
  public TraderArgument(String nodeName) {
    super(new StringArgument(nodeName), info -> {
      ShopTrader trader = ShopTrader.getTrader(info.input());

      if (trader == null) {
        throw CustomArgument.CustomArgumentException.fromMessageBuilder(new CustomArgument.MessageBuilder("Unknown trader: ").appendArgInput());
      } else {
        return trader;
      }
    });

    this.replaceSuggestions(ArgumentSuggestions.strings(info -> RegistryService.getInstance().getRegisteredTraders().stream().map(ShopTrader::getID).toArray(String[]::new))
    );
  }
}