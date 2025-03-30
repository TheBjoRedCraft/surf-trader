package dev.slne.surf.trader.bukkit.implementation

import com.google.auto.service.AutoService
import dev.slne.surf.trader.api.SurfTraderApi
import dev.slne.surf.trader.api.result.TradeResult
import dev.slne.surf.trader.api.trade.SurfTrade
import dev.slne.surf.trader.api.trader.SurfTrader
import dev.slne.surf.trader.core.service.cooldownService
import dev.slne.surf.trader.core.service.tradeService
import it.unimi.dsi.fastutil.objects.ObjectArraySet
import it.unimi.dsi.fastutil.objects.ObjectSet
import net.kyori.adventure.util.Services.Fallback
import org.bukkit.entity.Player

@AutoService(SurfTraderApi::class)
class BukkitSurfTraderApi(): SurfTraderApi, Fallback {
    override val loadedTraders: ObjectSet<SurfTrader> = ObjectArraySet()

    override fun getCooldown(player: Player, trade: SurfTrade): Long {
        return cooldownService.getCooldown(player, trade)
    }

    override fun setCooldown(player: Player, trade: SurfTrade, cooldown: Long) {
        cooldownService.setCooldown(player, trade, cooldown)
    }

    override fun isOnCooldown(player: Player, trade: SurfTrade): Boolean {
        return cooldownService.isOnCooldown(player, trade)
    }

    override fun spawnTrader(surfTrader: SurfTrader) {
        TODO("Not yet implemented")
    }

    override fun despawnTrader(surfTrader: SurfTrader) {
        TODO("Not yet implemented")
    }

    override fun handleJoin(player: Player) {
        TODO("Not yet implemented")
    }

    override suspend fun trade(player: Player, trade: SurfTrade): TradeResult {
        return tradeService.trade(player, trade)
    }
}