package dev.slne.surf.trader.service;

import dev.slne.surf.trader.annotation.Trade;
import dev.slne.surf.trader.annotation.Trader;
import dev.slne.surf.trader.obj.ShopTrade;
import dev.slne.surf.trader.obj.ShopTrader;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectSet;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;

import org.reflections.Reflections;

@Slf4j
@Getter
public class RegistryService {
  @Getter
  private static final RegistryService instance = new RegistryService();

  public static final String PACKAGE_NAME = "dev.slne.surf.trader";

  private final ObjectSet<ShopTrader> registeredTraders = new ObjectArraySet<>();
  private final ObjectSet<ShopTrade> registeredTrades = new ObjectArraySet<>();
  private final ComponentLogger logger = ComponentLogger.logger(this.getClass());

  private int registeredObjects = 0;

  public void registerAll() {
    long startTime = System.currentTimeMillis();

    registerTraders();
    registerTrades();

    long endTime = System.currentTimeMillis();

    logger.info(Component.text("Registered " + registeredObjects + " objects in " + (endTime - startTime) + "ms", NamedTextColor.GREEN));
  }

  private void registerTraders() {
    Reflections reflections = new Reflections(PACKAGE_NAME);
    ObjectSet<Class<?>> traderClasses = new ObjectArraySet<>(reflections.getTypesAnnotatedWith(Trader.class));

    for (Class<?> clazz : traderClasses) {
      try {
        Object traderInstance = clazz.getDeclaredConstructor().newInstance();
        registeredTraders.add((ShopTrader) traderInstance);

        registeredObjects ++;

      } catch (Exception e) {
        logger.error(Component.text("Failed to register trade " + clazz.getName(), NamedTextColor.RED));
      }
    }
  }

  private void registerTrades() {
    Reflections reflections = new Reflections(PACKAGE_NAME);
    ObjectSet<Class<?>> tradeClasses = new ObjectArraySet<>(reflections.getTypesAnnotatedWith(Trade.class));

    for (Class<?> clazz : tradeClasses) {
      try {
        Object tradeInstance = clazz.getDeclaredConstructor().newInstance();
        registeredTrades.add((ShopTrade) tradeInstance);

        registeredObjects ++;

      } catch (Exception e) {
        logger.error(Component.text("Failed to register trade " + clazz.getName(), NamedTextColor.RED));
      }
    }
  }
}
