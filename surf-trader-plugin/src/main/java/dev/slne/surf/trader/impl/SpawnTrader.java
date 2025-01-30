package dev.slne.surf.trader.impl;

import dev.slne.surf.trader.SurfTrader;
import dev.slne.surf.trader.annotation.Trader;
import dev.slne.surf.trader.impl.menu.SpawnTraderMenu;
import dev.slne.surf.trader.obj.MenuableTrader;
import dev.slne.surf.trader.obj.ShopTrade;
import dev.slne.surf.trader.obj.ShopTrader;
import dev.slne.surf.trader.obj.SpawnableTrader;

import dev.slne.surf.trader.util.PluginColor;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectSet;

import java.util.UUID;
import lol.pyr.znpcsplus.api.NpcApi;
import lol.pyr.znpcsplus.api.NpcApiProvider;

import lol.pyr.znpcsplus.api.entity.EntityProperty;
import lol.pyr.znpcsplus.api.npc.NpcEntry;
import lol.pyr.znpcsplus.api.npc.NpcType;
import lol.pyr.znpcsplus.api.skin.SkinDescriptor;
import lol.pyr.znpcsplus.util.LookType;
import lol.pyr.znpcsplus.util.NpcLocation;
import lombok.Getter;

import net.kyori.adventure.text.Component;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

@Trader
@Getter
public class SpawnTrader implements ShopTrader, MenuableTrader, SpawnableTrader {
  private final Component name = Component.text("Spawn Trader");
  private final ObjectSet<ShopTrade> trades = new ObjectArraySet<>();
  private final NpcApi npcApi = NpcApiProvider.get();

  private final String signature = "G/JtpyiQxNFn2ucGK1sHz7NZlxJhFeLzXNlCFCokL26N4gOg53Cwq2jowVSIUcDwxDxl43+ehMJzPjU5+DCVsoO5xJh5Zsh/98wpkWCE9PcOw/iAWTrcghEjusV5prlqS8QLAH82+ndP746PkNIyVhJlc8l5BACwRFbI3Nd2X2KwqyRJDWSbhPMl/V1Sg/mwGAl+IMzPjdvriN0CJDwqKv9gVugeLSmqFvlNIerwlkgyZqaaKtIYv/zRkaVmbFW0Dv8KxxAlH2zTpicY/F8aof4NjrvwajeuKd6z0jopnS+nNv+b6bqNcAnhbQWlTKz2V3mgzNZT2NjoTNopHa5FzJ+uEzSV9xr3XCrmFIM8PRoDLJEYOSFR2btA6V4PO0Hksdr5sags3lGGHihY+DTYKwNnQsJeL8jKBi12IHtmZ6HJvPkcPElNIzKVaIqgi5w3WEUv6QW3Dfp6U1Q77SrEzBg3d4I3ZSgXfN5L+B6qrE4EKyLCb9jZQD567CxVnOyPEz3k5bo0D/YOd9X75wWKFEnXmVO2UCxFlrueIAJZX+tEjbQM6jnfbkTctURl7qT5tvGhjMowE5arwN0PrZQ8Y1DzLv0FPVS+wkYvpyMURr3pd/pzKcy+60QjUVowzZKWCFtfGkV8Q/KkmYhmkHfSa+njPWMGhJEKkq3ecY8DQ0g==";
  private final String texture = "ewogICJ0aW1lc3RhbXAiIDogMTYxMzA5NDI3OTAzNSwKICAicHJvZmlsZUlkIiA6ICJhOGExZGY5MGE3YjU0NTdjYTEwYjM5ZGQ0N2NmMjM5MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJrZW5ueWtjb21haW4iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2NmOTU4NjQyMmM4NmI5ODk5N2QxMTYyNzc2OTYzNDcyZTQ1YzE4MzE0Y2Q3ODY1ZmY5NzYxOGE5MzdkYmQ2OCIKICAgIH0KICB9Cn0=";

  private final EntityProperty<SkinDescriptor> skinProperty = npcApi.getPropertyRegistry().getByName("skin", SkinDescriptor.class);
  private final EntityProperty<LookType> lookProperty = npcApi.getPropertyRegistry().getByName("look", LookType.class);
  private final SkinDescriptor skin = npcApi.getSkinDescriptorFactory().createStaticDescriptor(texture, signature);

  public static final String STRING_NAME = "<b><gradient:#369d91:#306965>ѕᴘᴀᴡɴ ᴛʀᴀᴅᴇʀ";
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
    new SpawnTraderMenu().show(player);
  }

  @Override
  public void spawn(Location location) {
    NpcType playerNpcType = npcApi.getNpcTypeRegistry().getByName("player");

    NpcEntry playerNpc = npcApi.getNpcRegistry().create(
        UUID.randomUUID().toString(),
        location.getWorld(),
        playerNpcType,
        new NpcLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch())
    );

    playerNpc.getNpc().getHologram().insertLine(0, STRING_NAME);
    playerNpc.getNpc().setProperty(skinProperty, npcApi.getSkinDescriptorFactory().createStaticDescriptor("Trader"));
    playerNpc.getNpc().setProperty(lookProperty, LookType.PER_PLAYER);
    playerNpc.getNpc().setProperty(SurfTrader.getInstance().getTraderProperty(), STRING_ID);
    playerNpc.enableEverything();
  }

  @Override
  public void remove(World world) {
    if(npcApi.getPropertyRegistry().getByName(SurfTrader.getInstance().getTRADER_PROPERTY()) == null) {
      Bukkit.getConsoleSender().sendMessage(Component.text("SurfTrader -> Property not found: " + STRING_ID, PluginColor.RED));
      return;
    }

    npcApi.getNpcRegistry()
        .getAll()
        .stream()
        .filter(npc -> npc.getNpc().hasProperty(npcApi.getPropertyRegistry().getByName(SurfTrader.getInstance().getTRADER_PROPERTY())))
        .filter(npc -> npc.getNpc().getProperty(npcApi.getPropertyRegistry().getByName(SurfTrader.getInstance().getTRADER_PROPERTY())).equals(STRING_ID))
        .forEach(npc -> npcApi.getNpcRegistry().delete(npc.getId()));
  }
}
