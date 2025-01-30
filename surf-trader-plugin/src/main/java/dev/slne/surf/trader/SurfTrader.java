package dev.slne.surf.trader;

import dev.slne.surf.trader.command.SurfTraderCommand;
import dev.slne.surf.trader.impl.SpawnTrader;
import dev.slne.surf.trader.listener.PlayerInteractListener;
import dev.slne.surf.trader.service.AuxProtectService;
import dev.slne.surf.trader.service.RegistryService;

import lol.pyr.znpcsplus.api.NpcApi;
import lol.pyr.znpcsplus.api.NpcApiProvider;
import lol.pyr.znpcsplus.api.entity.EntityProperty;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class SurfTrader extends JavaPlugin {
  private static final String[] SUPPORTED_VERSIONS = new String[] { "1.21.4", "1.21.5" };
  private final String TRADER_PROPERTY = "trader";
  private EntityProperty<String> traderProperty;
  private NpcApi npcApi;

  @Override
  public void onEnable() {
    this.npcApi = NpcApiProvider.get();
    this.traderProperty = npcApi.getPropertyRegistry().getByName("trader", String.class);

    AuxProtectService.getInstance().createAction();
    RegistryService.getInstance().registerAll();

    this.registerCommands();
    this.registerListener();

    this.checkVersion();
    this.createProperties();
  }

  private void registerListener() {
    Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
  }

  private void registerCommands() {
    new SurfTraderCommand("surftrader").register();
  }

  private void checkVersion() {
    String version = Bukkit.getMinecraftVersion();
    boolean pass = false;

    for (String supportedVersion : SUPPORTED_VERSIONS) {
      if(version.contains(supportedVersion)) {
        pass = true;
        break;
      }
    }

    if(!pass) {
      Bukkit.getConsoleSender().sendMessage(Component.text("> SurfTrader | This version of Minecraft is not supported by SurfTrader", NamedTextColor.RED).decorate(TextDecoration.BOLD));
      Bukkit.getPluginManager().disablePlugin(this);
    }
  }

  public void createProperties() {
    if(traderProperty == null) {
      npcApi.getPropertyRegistry().registerDummy(this.TRADER_PROPERTY, String.class);
      this.traderProperty = npcApi.getPropertyRegistry().getByName(this.TRADER_PROPERTY, String.class);
    }
  }

  public static SurfTrader getInstance() {
    return getPlugin(SurfTrader.class);
  }
}
