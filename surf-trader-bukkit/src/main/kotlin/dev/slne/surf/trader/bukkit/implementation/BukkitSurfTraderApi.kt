package dev.slne.surf.trader.bukkit.implementation

import com.google.auto.service.AutoService
import dev.slne.surf.trader.api.SurfTraderApi
import dev.slne.surf.trader.api.result.TradeResult
import dev.slne.surf.trader.api.trade.SurfTrade
import dev.slne.surf.trader.core.service.cooldownService
import dev.slne.surf.trader.core.service.tradeService
import net.kyori.adventure.util.Services.Fallback
import org.bukkit.entity.Player

@AutoService(SurfTraderApi::class)
class BukkitSurfTraderApi(): SurfTraderApi, Fallback {
    override fun getCooldown(player: Player, trade: SurfTrade): Long {
        return cooldownService.getCooldown(player, trade)
    }

    override fun setCooldown(player: Player, trade: SurfTrade, cooldown: Long) {
        cooldownService.setCooldown(player, trade, cooldown)
    }

    override fun isOnCooldown(player: Player, trade: SurfTrade): Boolean {
        return cooldownService.isOnCooldown(player, trade)
    }

    override suspend fun trade(player: Player, trade: SurfTrade): TradeResult {
        return tradeService.trade(player, trade)
    }
}