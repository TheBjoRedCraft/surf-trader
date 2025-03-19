package dev.slne.surf.trader.core.service

import dev.slne.surf.surfapi.core.api.util.requiredService
import dev.slne.surf.trader.api.trade.SurfTrade
import org.bukkit.entity.Player

import java.util.UUID

interface CooldownService {
    fun getCooldown(player: Player, trade: SurfTrade): Long
    fun setCooldown(player: Player, trade: SurfTrade, cooldown: Long)
    fun isOnCooldown(player: Player, trade: SurfTrade): Boolean

    companion object {
        val INSTANCE = requiredService<CooldownService>()
    }
}

val cooldownService get() = CooldownService.INSTANCE