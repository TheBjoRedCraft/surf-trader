package dev.slne.surf.trader.command.argument;

import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.CustomArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.slne.surf.trader.obj.ShopTrade;
import dev.slne.surf.trader.obj.ShopTrader;
import dev.slne.surf.trader.service.RegistryService;

public class TradeArgument extends CustomArgument<ShopTrade, String> {
  public TradeArgument(String nodeName) {
    super(new StringArgument(nodeName), info -> {
      ShopTrade trade = ShopTrade.getTrade(info.input());

      if (trade == null) {
        throw CustomArgumentException.fromMessageBuilder(new MessageBuilder("Unknown trade: ").appendArgInput());
      } else {
        return trade;
      }
    });

    this.replaceSuggestions(ArgumentSuggestions.strings(info -> RegistryService.getInstance().getRegisteredTrades().stream().map(ShopTrade::getID).toArray(String[]::new))
    );
  }
}