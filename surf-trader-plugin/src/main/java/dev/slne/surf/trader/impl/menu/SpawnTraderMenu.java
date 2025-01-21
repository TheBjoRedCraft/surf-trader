package dev.slne.surf.trader.impl.menu;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import dev.slne.surf.trader.service.TradeService;
import dev.slne.surf.trader.util.ItemBuilder;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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

    final ItemStack globeBannerItem = new ItemBuilder(Material.GLOBE_BANNER_PATTERN)
        .setName(Component.text("Globe Banner Vorlage"))
        .setCustomModelData(331)
        .build();

    final ItemStack invisibleItemFrame = new ItemBuilder(Material.ITEM_FRAME)
        .setName(Component.text("Unsichtbarer Item Rahmen"))
        .setCustomModelData(331)
        .build();

    final ItemStack lightBlock = new ItemBuilder(Material.LIGHT)
        .setName(Component.text("Licht Block"))
        .setCustomModelData(331)
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

    addPane(topLeft);
    addPane(topRight);
    addPane(bottomLeft);
    addPane(bottomRight);
  }
}
