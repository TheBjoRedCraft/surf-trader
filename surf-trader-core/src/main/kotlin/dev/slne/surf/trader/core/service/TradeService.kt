package dev.slne.surf.trader.core.service

import dev.slne.surf.surfapi.core.api.util.requiredService
import dev.slne.surf.trader.api.SurfTraderApi
import dev.slne.surf.trader.api.result.TradeResult
import dev.slne.surf.trader.api.trade.SurfTrade
import dev.slne.surf.trader.api.trader.SurfTrader
import it.unimi.dsi.fastutil.objects.ObjectList
import org.bukkit.entity.Player
import java.util.UUID

interface TradeService {
    suspend fun trade(player: Player, trade: SurfTrade): TradeResult

    companion object {
        val INSTANCE = requiredService<TradeService>()
    }
}

val tradeService get() = TradeService.INSTANCE