package dev.slne.surf.trader.impl;

import dev.slne.surf.trader.annotation.Trader;
import dev.slne.surf.trader.impl.menu.SpawnTraderMenu;
import dev.slne.surf.trader.obj.MenuableTrader;
import dev.slne.surf.trader.obj.ShopTrade;
import dev.slne.surf.trader.obj.ShopTrader;
import dev.slne.surf.trader.obj.SpawnableTrader;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectSet;

import lombok.Getter;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.LookClose;

import net.kyori.adventure.text.Component;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

@Trader
@Getter
public class SpawnTrader implements ShopTrader, MenuableTrader, SpawnableTrader {
  private final Component name = Component.text("Spawn Trader");
  private final ObjectSet<ShopTrade> trades = new ObjectArraySet<>();

  private final String signature = "G/JtpyiQxNFn2ucGK1sHz7NZlxJhFeLzXNlCFCokL26N4gOg53Cwq2jowVSIUcDwxDxl43+ehMJzPjU5+DCVsoO5xJh5Zsh/98wpkWCE9PcOw/iAWTrcghEjusV5prlqS8QLAH82+ndP746PkNIyVhJlc8l5BACwRFbI3Nd2X2KwqyRJDWSbhPMl/V1Sg/mwGAl+IMzPjdvriN0CJDwqKv9gVugeLSmqFvlNIerwlkgyZqaaKtIYv/zRkaVmbFW0Dv8KxxAlH2zTpicY/F8aof4NjrvwajeuKd6z0jopnS+nNv+b6bqNcAnhbQWlTKz2V3mgzNZT2NjoTNopHa5FzJ+uEzSV9xr3XCrmFIM8PRoDLJEYOSFR2btA6V4PO0Hksdr5sags3lGGHihY+DTYKwNnQsJeL8jKBi12IHtmZ6HJvPkcPElNIzKVaIqgi5w3WEUv6QW3Dfp6U1Q77SrEzBg3d4I3ZSgXfN5L+B6qrE4EKyLCb9jZQD567CxVnOyPEz3k5bo0D/YOd9X75wWKFEnXmVO2UCxFlrueIAJZX+tEjbQM6jnfbkTctURl7qT5tvGhjMowE5arwN0PrZQ8Y1DzLv0FPVS+wkYvpyMURr3pd/pzKcy+60QjUVowzZKWCFtfGkV8Q/KkmYhmkHfSa+njPWMGhJEKkq3ecY8DQ0g==";
  private final String texture = "ewogICJ0aW1lc3RhbXAiIDogMTYxMzA5NDI3OTAzNSwKICAicHJvZmlsZUlkIiA6ICJhOGExZGY5MGE3YjU0NTdjYTEwYjM5ZGQ0N2NmMjM5MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJrZW5ueWtjb21haW4iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2NmOTU4NjQyMmM4NmI5ODk5N2QxMTYyNzc2OTYzNDcyZTQ1YzE4MzE0Y2Q3ODY1ZmY5NzYxOGE5MzdkYmQ2OCIKICAgIH0KICB9Cn0=";

  public static final String STRING_NAME = "§l<gradient:#369d91:#306965>ѕᴘᴀᴡɴ ᴛʀᴀᴅᴇʀ";
  public static final String STRING_ID = "spawntrader";


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
    NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, STRING_NAME);

    npc.getOrAddTrait(LookClose.class).setPerPlayer(true);
    npc.getOrAddTrait(LookClose.class).setRealisticLooking(true);
    npc.getOrAddTrait(LookClose.class).lookClose(true);
    npc.spawn(location);
    //npc.getOrAddTrait(SkinTrait.class).setSkinPersistent("Trader", signature, texture);
    npc.getEntity().getScoreboardTags().add(STRING_ID);
  }
}
