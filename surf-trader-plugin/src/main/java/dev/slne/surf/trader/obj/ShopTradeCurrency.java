package dev.slne.surf.trader.obj;

import lombok.Getter;

@Getter
public enum ShopTradeCurrency {
  ITEM("item"),
  CAST_COINS("cast_coins"),
  EVENT_COINS("event_coins");

  private final String name;

  ShopTradeCurrency(String name) {
    this.name = name;
  }
}
