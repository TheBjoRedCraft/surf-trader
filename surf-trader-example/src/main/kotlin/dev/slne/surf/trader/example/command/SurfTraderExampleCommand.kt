package dev.slne.surf.trader.example.command

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.integerArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor

import dev.slne.surf.surfapi.bukkit.api.builder.buildItem
import dev.slne.surf.surfapi.bukkit.api.builder.displayName
import dev.slne.surf.trader.api.requirement.SurfTradeTransactionRequirement
import dev.slne.surf.trader.api.reward.SurfTradeItemReward
import dev.slne.surf.trader.api.reward.SurfTradeTransactionReward
import dev.slne.surf.trader.api.surfTraderApi
import dev.slne.surf.trader.api.trade.SurfTrade
import dev.slne.surf.trader.api.util.VisualTransaction
import dev.slne.surf.trader.core.service.reward.SurfTradeItemRequirement
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

            val exampleTransactionTradeRequirement = SurfTradeTransactionRequirement(VisualTransaction(20.toBigDecimal(), transactionApi.getDefaultCurrency() ?: return@playerExecutor))
            val exampleTransactionTradeReward = SurfTradeItemReward(buildItem(Material.DIAMOND){ displayName(Component.text("Danke für den Einkauf")) })
            val exampleTransactionTrade = SurfTrade(
                UUID.randomUUID(),
                "example-trade-transaction-$id",
                Component.text("Example Transaction Trade"),
                Component.text("An example trade for transaction trades"),
                100L,
                ObjectArrayList.of(exampleTransactionTradeRequirement),
                ObjectArrayList.of(exampleTransactionTradeReward)
            )

            val exampleItemTradeRequirement = SurfTradeItemRequirement(buildItem(Material.NETHER_STAR) { amount = 10 })
            val exampleItemTradeReward = SurfTradeTransactionReward(VisualTransaction(100.toBigDecimal(), transactionApi.getDefaultCurrency() ?: return@playerExecutor))
            val exampleItemTrade = SurfTrade(
                UUID.randomUUID(),
                "example-trade-item-$id",
                Component.text("Example Item Trade"),
                Component.text("An example trade for item trades"),
                100L,
                ObjectArrayList.of(exampleItemTradeRequirement),
                ObjectArrayList.of(exampleItemTradeReward)
            )

            surfTraderApi.spawnTrader(SurfExampleTrader(UUID.randomUUID(),
                "Example Trader",
                Component.text("Example Trader"),
                ObjectArrayList.of(exampleTransactionTrade, exampleItemTrade),
                "TheBjoRedCraft"
            ),
                player.location
            )

            player.sendMessage(Component.text("Spawned."))
        }
    }
}