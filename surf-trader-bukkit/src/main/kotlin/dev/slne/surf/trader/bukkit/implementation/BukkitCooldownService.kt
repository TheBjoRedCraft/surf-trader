package dev.slne.surf.trader.bukkit.implementation

import com.google.auto.service.AutoService
import dev.slne.surf.trader.api.trade.SurfTrade
import dev.slne.surf.trader.core.service.CooldownService
import net.kyori.adventure.util.Services.Fallback
import org.bukkit.entity.Player

@AutoService(CooldownService::class)
class BukkitCooldownService(): CooldownService, Fallback {
    override fun getCooldown(player: Player, trade: SurfTrade): Long {
        TODO("Not yet implemented")
    }

    override fun setCooldown(player: Player, trade: SurfTrade, cooldown: Long) {
        TODO("Not yet implemented")
    }

    override fun isOnCooldown(player: Player, trade: SurfTrade): Boolean {
        TODO("Not yet implemented")
    }
}