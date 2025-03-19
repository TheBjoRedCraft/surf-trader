package dev.slne.surf.trader.bukkit.implementation

import com.google.auto.service.AutoService
import dev.slne.surf.trader.api.trade.SurfTrade
import dev.slne.surf.trader.core.service.TradeService
import net.kyori.adventure.util.Services.Fallback
import org.bukkit.entity.Player

@AutoService(TradeService::class)
class BukkitTradeService(): TradeService, Fallback {
    override fun trade(player: Player, trade: SurfTrade) {
        TODO("Not yet implemented")
    }
}