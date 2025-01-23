package dev.slne.surf.trader;

import dev.slne.surf.trader.command.SurfTraderCommand;
import dev.slne.surf.trader.listener.PlayerInteractListener;
import dev.slne.surf.trader.service.RegistryService;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SurfTrader extends JavaPlugin {
  private static final String[] SUPPORTED_VERSIONS = new String[] { "1.21.4", "1.21.5" };

  @Override
  public void onEnable() {
    //AuxProtectService.getInstance().createAction();

    RegistryService.getInstance().registerAll();

    this.registerCommands();
    this.registerListener();

    this.checkVersion();
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

  public static SurfTrader getInstance() {
    return getPlugin(SurfTrader.class);
  }
}
