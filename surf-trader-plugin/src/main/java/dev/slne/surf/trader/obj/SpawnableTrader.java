package dev.slne.surf.trader.obj;

import org.bukkit.Location;
import org.bukkit.World;

public interface SpawnableTrader {
  void spawn(Location location);
  void remove(World world);
}
