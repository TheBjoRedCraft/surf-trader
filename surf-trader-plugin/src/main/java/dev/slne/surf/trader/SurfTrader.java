package dev.slne.surf.trader;

import dev.slne.surf.trader.command.SurfTraderCommand;
import dev.slne.surf.trader.listener.PlayerInteractListener;
import dev.slne.surf.trader.service.RegistryService;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SurfTrader extends JavaPlugin {

  @Override
  public void onEnable() {
    //AuxProtectService.getInstance().createAction();

    RegistryService.getInstance().registerAll();

    this.registerCommands();
    this.registerListener();
  }

  private void registerListener() {
    Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
  }

  private void registerCommands() {
    new SurfTraderCommand("surftrader").register();
  }

  public static SurfTrader getInstance() {
    return getPlugin(SurfTrader.class);
  }
}
