package dev.slne.surf.trader.impl;

import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.NpcData;
import de.oliver.fancynpcs.api.utils.SkinFetcher.SkinData;
import dev.slne.surf.trader.annotation.Trader;
import dev.slne.surf.trader.impl.menu.SpawnTraderMenu;
import dev.slne.surf.trader.obj.MenuableTrader;
import dev.slne.surf.trader.obj.ShopTrade;
import dev.slne.surf.trader.obj.ShopTrader;
import dev.slne.surf.trader.obj.SpawnableTrader;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectSet;

import java.util.UUID;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Trader
public class SpawnTrader implements ShopTrader, MenuableTrader, SpawnableTrader {
  private final Component name = Component.text("Spawn Trader");
  private final ObjectSet<ShopTrade> trades = new ObjectArraySet<>();

  public static final String STRING_NAME = "Spawn Trader";
  public static final String STRING_ID = "SpawnTrader";


  @Override
  public String getID() {
    return STRING_ID;
  }

  @Override
  public ObjectSet<ShopTrade> getTrades() {
    return this.trades;
  }

  @Override
  public void addTrade(ShopTrade trade) {
    this.trades.add(trade);
  }

  @Override
  public void removeTrade(ShopTrade trade) {
    this.trades.remove(trade);
  }

  @Override
  public void openMenu(Player player) {
    new SpawnTraderMenu(player).show(player);
  }

  @Override
  public void spawn(Location location) {
    NpcData data = new NpcData(this.getID() + UUID.randomUUID(), UUID.randomUUID(), location);
    data.setSkin(new SkinData("<gold><b>Spawn Trader", null, null ));
    data.setDisplayName(STRING_NAME);

    Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(data);
    FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);

    npc.create();
    npc.spawnForAll();
  }
}
