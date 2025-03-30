package dev.slne.surf.trader.core.service

import dev.slne.surf.surfapi.core.api.util.requiredService
import dev.slne.surf.trader.api.result.TradeResult
import dev.slne.surf.trader.api.trade.SurfTrade
import org.bukkit.entity.Player

interface TradeService {
    suspend fun trade(player: Player, trade: SurfTrade): TradeResult

    companion object {
        val INSTANCE = requiredService<TradeService>()
    }
}

val tradeService get() = TradeService.INSTANCE