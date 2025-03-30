package dev.slne.surf.trader.example.menu

import com.github.retrooper.packetevents.protocol.component.ComponentTypes
import com.github.retrooper.packetevents.protocol.component.builtin.item.ItemLore
import com.github.retrooper.packetevents.protocol.item.ItemStack
import com.github.retrooper.packetevents.protocol.item.type.ItemTypes
import com.github.shynixn.mccoroutine.folia.launch

import dev.slne.surf.gui.common.toComponent
import dev.slne.surf.gui.menu.button.Button
import dev.slne.surf.gui.menu.item.ItemBuilder
import dev.slne.surf.gui.menu.menu.Menu
import dev.slne.surf.gui.menu.menu.MenuType
import dev.slne.surf.trader.api.surfTraderApi
import dev.slne.surf.trader.example.SurfExampleTrader
import dev.slne.surf.trader.example.plugin
import dev.slne.surf.transaction.api.transactionApi

import org.bukkit.entity.Player

class SurfTraderExampleMenu(): Menu (
    name = "<gradient:red:yellow>Example Surf Trader Menu".toComponent(),
    type = MenuType.GENERIC9X5,
    mapOf(
        Pair(11, Button(
            item = ItemBuilder().itemType(ItemTypes.DIAMOND).amount(32).lore("<green>+ 1 Diamond".toComponent(), "<red>-20 Coins".toComponent()).build())
        ),
        Pair(15, Button(
            item = ItemStack.builder().type(ItemTypes.GOLD_NUGGET).amount(32).component(ComponentTypes.LORE, ItemLore(listOf("<green>+ 32 Coins".toComponent(), "<red>-20 Nether Stars".toComponent()))).build(),
            execute = {
                plugin.launch {
                    surfTraderApi.trade(it.user.player as Player, SurfExampleTrader.getExampleItemTrade(transactionApi.getDefaultCurrency() ?: return@launch))
                }
            }
        ))
    )){
}