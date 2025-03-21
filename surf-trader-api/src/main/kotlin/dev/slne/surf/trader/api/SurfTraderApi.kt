package dev.slne.surf.trader.api

import dev.slne.surf.surfapi.core.api.util.requiredService
import dev.slne.surf.trader.api.result.TradeResult
import dev.slne.surf.trader.api.trade.SurfTrade
import org.bukkit.entity.Player

interface SurfTraderApi {

    fun getCooldown(player: Player, trade: SurfTrade): Long
    fun setCooldown(player: Player, trade: SurfTrade, cooldown: Long)
    fun isOnCooldown(player: Player, trade: SurfTrade): Boolean

    suspend fun trade(player: Player, trade: SurfTrade): TradeResult

    companion object {
        val INSTANCE = requiredService<SurfTraderApi>()
    }
}

val surfTraderApi get() = SurfTraderApi.INSTANCE