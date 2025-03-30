package dev.slne.surf.trader.example.command

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor

import dev.slne.surf.trader.api.surfTraderApi
import dev.slne.surf.trader.example.SurfExampleTrader
import dev.slne.surf.transaction.api.transactionApi

import it.unimi.dsi.fastutil.objects.ObjectArrayList

import net.kyori.adventure.text.Component
import java.util.*

class SurfTraderExampleCommand(commandName: String): CommandAPICommand(commandName) {
    init {
        playerExecutor { player, _ ->
            surfTraderApi.spawnTrader(
                SurfExampleTrader(
                    UUID.randomUUID(),
                    "Example Trader",
                    Component.text("Example Trader"),
                    ObjectArrayList.of(
                        SurfExampleTrader.getExampleTransactionTrade(transactionApi.getDefaultCurrency() ?: return@playerExecutor),
                        SurfExampleTrader.getExampleItemTrade(transactionApi.getDefaultCurrency() ?: return@playerExecutor)
                    ),
                    "TheBjoRedCraft"
                  ),
                player.location
            )

            player.sendMessage(Component.text("Spawned."))
        }
    }
}