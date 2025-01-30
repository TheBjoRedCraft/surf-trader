package dev.slne.surf.trader.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public class TimeUtil {
  public static Component calcEnding(long ending, TextColor color) {
    int remaining = (int) (ending - System.currentTimeMillis());
    int seconds = remaining / 1000;
    int minutes = seconds / 60;
    int hours = minutes / 60;

    seconds %= 60;
    minutes %= 60;

    Component result = Component.empty();

    if (hours > 0) {
      result = result.append(Component.text(hours + " " + (hours == 1 ? "Stunde" : "Stunden"), color));
    }

    if (minutes > 0) {
      if (!result.equals(Component.empty())) {
        result = result.append(Component.text(" ", NamedTextColor.GRAY));
      }
      result = result.append(Component.text(minutes + " " + (minutes == 1 ? "Minute" : "Minuten"), color));
    }

    if (seconds > 0 || result.equals(Component.empty())) {
      if (!result.equals(Component.empty())) {
        result = result.append(Component.text(" ", NamedTextColor.GRAY));
      }
      result = result.append(Component.text(seconds + " " + (seconds == 1 ? "Sekunde" : "Sekunden"), color));
    }

    return result;
  }
}
