package dev.slne.surf.trader.example.command

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.integerArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor

import dev.slne.surf.surfapi.bukkit.api.builder.buildItem
import dev.slne.surf.surfapi.bukkit.api.builder.displayName
import dev.slne.surf.trader.api.requirement.SurfTradeTransactionRequirement
import dev.slne.surf.trader.api.reward.SurfTradeItemReward
import dev.slne.surf.trader.api.surfTraderApi
import dev.slne.surf.trader.api.trade.SurfTrade
import dev.slne.surf.trader.api.util.VisualTransaction
import dev.slne.surf.trader.example.SurfExampleTrader
import dev.slne.surf.transaction.api.transactionApi

import it.unimi.dsi.fastutil.objects.ObjectArrayList

import net.kyori.adventure.text.Component

import org.bukkit.Material
import java.math.BigDecimal
import java.util.*

class SurfTraderExampleCommand(commandName: String): CommandAPICommand(commandName) {
    init {
        integerArgument("id")
        playerExecutor { player, args ->
            val id: Int by args

            surfTraderApi.spawnTrader(SurfExampleTrader(UUID.randomUUID(),
                "Example Trader",
                Component.text("Example Trader"),
                ObjectArrayList.of(
                    SurfTrade(
                        UUID.randomUUID(),
                        "example-trade-transaction-$id",
                        Component.text("Example Trade Transaction"),
                        Component.text("An example trade for transaction trades"),
                        100L,
                        ObjectArrayList.of(
                            SurfTradeTransactionRequirement(VisualTransaction(BigDecimal.valueOf(20), transactionApi.getDefaultCurrency() ?: return@playerExecutor))
                        ),
                        ObjectArrayList.of(
                            SurfTradeItemReward(buildItem(Material.DIAMOND){
                                displayName(Component.text("Danke für den Einkauf"))
                            })
                        )
                        )
                    )
                )
            )

            player.sendMessage(Component.text("Spawned."))
        }
    }
}