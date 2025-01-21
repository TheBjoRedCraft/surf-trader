package dev.slne.surf.trader.impl.menu;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import dev.slne.surf.trader.service.TradeService;
import dev.slne.surf.trader.util.ItemBuilder;
import dev.slne.surf.trader.util.PluginColor;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;

@Getter
public class SpawnTraderMenu extends ChestGui {
  private final TradeService tradeService = TradeService.getInstance();

  public SpawnTraderMenu(Player player) {
    super(6, "Spawn Trader");

    final OutlinePane topLeft = new OutlinePane(0, 0, 4, 3);
    final OutlinePane topRight = new OutlinePane(5, 0, 4, 3);
    final OutlinePane bottomLeft = new OutlinePane(0, 3, 4, 3);
    final OutlinePane bottomRight = new OutlinePane(5, 3, 4, 3);

    final ItemStack globeBannerItem = new ItemBuilder(Material.PAPER)
        .setName(Component.text("Globe Banner Vorlage"))
        .addLoreLine(Component.text("Mit dieser Banner Vorlage kannst du", PluginColor.LIGHT_GRAY))
        .addLoreLine(Component.text("deinen eigenen Banner mit dieser", PluginColor.LIGHT_GRAY))
        .addLoreLine(Component.text("Vorlage erstellen.", PluginColor.LIGHT_GRAY))
        .addLoreLine(Component.empty())
        .addLoreLine(Component.text("Du bekommst:", PluginColor.LIGHT_BLUE))
        .addLoreLine(Component.text("> ", PluginColor.LIGHT_GRAY).append(Component.text("1x Globe Banner", PluginColor.GOLD)))
        .addLoreLine(Component.empty())
        .addLoreLine(Component.text("Kosten:", PluginColor.LIGHT_BLUE))
        .addLoreLine(Component.text("> ", PluginColor.LIGHT_GRAY).append(Component.text("20x Papier", PluginColor.GOLD)))
        .addLoreLine(Component.text("> ", PluginColor.LIGHT_GRAY).append(Component.text("5x Smaragde", PluginColor.GOLD)))
        .addLoreLine(Component.empty())
        .addLoreLine(Component.text("Wiederverwendbar: ", PluginColor.LIGHT_BLUE).append(Component.text("❌", NamedTextColor.RED).decorate(TextDecoration.BOLD)))
        .setRarity(ItemRarity.EPIC)
        .setCustomModelData(330001)
        .build();

    final ItemStack invisibleItemFrame = new ItemBuilder(Material.PAPER)
        .setName(Component.text("Unsichtbarer Item Rahmen"))
        .addLoreLine(Component.text("Ein unsichtbarer Item Rahmen, nach", PluginColor.LIGHT_GRAY))
        .addLoreLine(Component.text("dem abbauen bleibt der Rahmen", PluginColor.LIGHT_GRAY))
        .addLoreLine(Component.text("unsichtbar.", PluginColor.LIGHT_GRAY))
        .addLoreLine(Component.empty())
        .addLoreLine(Component.text("Du bekommst:", PluginColor.LIGHT_BLUE))
        .addLoreLine(Component.text("> ", PluginColor.LIGHT_GRAY).append(Component.text("20x Unsichtbare Item Rahmen", PluginColor.GOLD)))
        .addLoreLine(Component.empty())
        .addLoreLine(Component.text("Kosten:", PluginColor.LIGHT_BLUE))
        .addLoreLine(Component.text("> ", PluginColor.LIGHT_GRAY).append(Component.text("20x Item Rahmen", PluginColor.GOLD)))
        .addLoreLine(Component.text("> ", PluginColor.LIGHT_GRAY).append(Component.text("5x Smaragde", PluginColor.GOLD)))
        .addLoreLine(Component.empty())
        .addLoreLine(Component.text("Wiederverwendbar: ", PluginColor.LIGHT_BLUE).append(Component.text("✔", NamedTextColor.GREEN).decorate(TextDecoration.BOLD)))
        .setRarity(ItemRarity.EPIC)
        .setCustomModelData(330001)
        .build();

    final ItemStack lightBlock = new ItemBuilder(Material.PAPER)
        .setName(Component.text("Licht Block"))
        .addLoreLine(Component.text("Eine unsichtbare Licht Quelle.", PluginColor.LIGHT_GRAY))
        .addLoreLine(Component.text("Diese kann pro Item nur einmal platziert", PluginColor.LIGHT_GRAY))
        .addLoreLine(Component.text("werden und ist nach dem Platzieren nicht", PluginColor.LIGHT_GRAY))
        .addLoreLine(Component.text("mehr sichtbar, sowie nicht mehr abbaubar.", PluginColor.LIGHT_GRAY))
        .addLoreLine(Component.text("Der Block leuchtet maximal 15 Blöcke weit.", PluginColor.LIGHT_GRAY))
        .addLoreLine(Component.empty())
        .addLoreLine(Component.text("Du bekommst:", PluginColor.LIGHT_BLUE))
        .addLoreLine(Component.text("> ", PluginColor.LIGHT_GRAY).append(Component.text("20x Licht Block", PluginColor.GOLD)))
        .addLoreLine(Component.empty())
        .addLoreLine(Component.text("Kosten:", PluginColor.LIGHT_BLUE))
        .addLoreLine(Component.text("> ", PluginColor.LIGHT_GRAY).append(Component.text("20x Redstone Lampe", PluginColor.GOLD)))
        .addLoreLine(Component.text("> ", PluginColor.LIGHT_GRAY).append(Component.text("5x Smaragde", PluginColor.GOLD)))
        .addLoreLine(Component.empty())
        .addLoreLine(Component.text("Wiederverwendbar: ", PluginColor.LIGHT_BLUE).append(Component.text("❌", NamedTextColor.RED).decorate(TextDecoration.BOLD)))
        .setRarity(ItemRarity.EPIC)
        .setCustomModelData(330001)
        .build();

    topLeft.addItem(new GuiItem(globeBannerItem));
    topLeft.setRepeat(true);

    bottomLeft.addItem(new GuiItem(lightBlock));
    bottomLeft.setRepeat(true);

    bottomRight.addItem(new GuiItem(invisibleItemFrame));
    bottomRight.setRepeat(true);

    topLeft.setOnClick(event -> {
      tradeService.buy((Player) event.getWhoClicked(), tradeService.getTrade(globeBannerItem));
    });

    bottomLeft.setOnClick(event -> {
      tradeService.buy((Player) event.getWhoClicked(), tradeService.getTrade(lightBlock));
    });

    bottomRight.setOnClick(event -> {
      tradeService.buy((Player) event.getWhoClicked(), tradeService.getTrade(invisibleItemFrame));
    });

    this.addPane(topLeft);
    this.addPane(topRight);
    this.addPane(bottomLeft);
    this.addPane(bottomRight);

    this.setOnGlobalClick(event -> event.setCancelled(true));
    this.setOnGlobalDrag(event -> event.setCancelled(true));
  }
}
