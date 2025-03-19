package dev.slne.surf.trader.bukkit.implementation

import com.google.auto.service.AutoService
import dev.slne.surf.trader.api.SurfTraderApi
import dev.slne.surf.trader.api.requirement.SurfTradeRequirement
import dev.slne.surf.trader.api.trade.SurfTrade
import net.kyori.adventure.util.Services.Fallback
import org.bukkit.entity.Player

@AutoService(SurfTraderApi::class)
class BukkitSurfTraderApi(): SurfTraderApi, Fallback {
    override fun getCooldown(player: Player, trade: SurfTrade): Long {
        TODO("Not yet implemented")
    }

    override fun setCooldown(player: Player, trade: SurfTrade, cooldown: Long) {
        TODO("Not yet implemented")
    }

    override fun isOnCooldown(player: Player, trade: SurfTrade): Boolean {
        TODO("Not yet implemented")
    }

    override fun trade(player: Player, trade: SurfTrade) {
        TODO("Not yet implemented")
    }

    override fun hasRequirement(player: Player, requirement: SurfTradeRequirement): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasRequirement(player: Player, trade: SurfTrade) {
        TODO("Not yet implemented")
    }
}