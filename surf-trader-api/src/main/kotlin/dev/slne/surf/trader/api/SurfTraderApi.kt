package dev.slne.surf.trader.api

import dev.slne.surf.surfapi.core.api.util.mutableObjectSetOf
import dev.slne.surf.surfapi.core.api.util.requiredService
import dev.slne.surf.trader.api.result.TradeResult
import dev.slne.surf.trader.api.trade.SurfTrade
import dev.slne.surf.trader.api.trader.SurfTrader
import it.unimi.dsi.fastutil.objects.ObjectArraySet
import it.unimi.dsi.fastutil.objects.ObjectSet
import org.bukkit.Location
import org.bukkit.entity.Player

interface SurfTraderApi {
    val loadedTraders: ObjectSet<SurfTrader>

    fun getCooldown(player: Player, trade: SurfTrade): Long
    fun setCooldown(player: Player, trade: SurfTrade, cooldown: Long)
    fun isOnCooldown(player: Player, trade: SurfTrade): Boolean

    fun spawnTrader(surfTrader: SurfTrader, location: Location)
    fun despawnTrader(surfTrader: SurfTrader)

    suspend fun trade(player: Player, trade: SurfTrade): TradeResult

    companion object {
        val INSTANCE = requiredService<SurfTraderApi>()
    }
}

val surfTraderApi get() = SurfTraderApi.INSTANCE