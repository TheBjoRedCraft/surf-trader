package dev.slne.surf.trader.api

import dev.slne.surf.surfapi.core.api.util.requiredService
import dev.slne.surf.trader.api.requirement.SurfTradeRequirement
import dev.slne.surf.trader.api.trade.SurfTrade
import dev.slne.surf.trader.api.trader.SurfTrader
import it.unimi.dsi.fastutil.objects.ObjectList
import org.bukkit.entity.Player

interface SurfTraderApi {

    fun getCooldown(player: Player, trade: SurfTrade): Long
    fun setCooldown(player: Player, trade: SurfTrade, cooldown: Long)
    fun isOnCooldown(player: Player, trade: SurfTrade): Boolean

    fun trade(player: Player, trade: SurfTrade)

    fun hasRequirement(player: Player, requirement: SurfTradeRequirement): Boolean
    fun hasRequirement(player: Player, trade: SurfTrade)


    companion object {
        val INSTANCE = requiredService<SurfTraderApi>()
    }
}

val surfTraderApi get() = SurfTraderApi.INSTANCE