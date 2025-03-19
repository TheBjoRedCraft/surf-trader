package dev.slne.surf.trader.bukkit.implementation

import com.google.auto.service.AutoService
import dev.slne.surf.trader.api.trade.SurfTrade
import dev.slne.surf.trader.core.service.CooldownService
import net.kyori.adventure.util.Services.Fallback
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType

@AutoService(CooldownService::class)
class BukkitCooldownService(): CooldownService, Fallback {
    override fun getCooldown(player: Player, trade: SurfTrade): Long {
        val pdc = player.persistentDataContainer
        val key = trade.cooldownKey

        return pdc.getOrDefault(key, PersistentDataType.LONG, 0L)
    }

    override fun setCooldown(player: Player, trade: SurfTrade, cooldown: Long) {
        val pdc = player.persistentDataContainer
        val key = trade.cooldownKey

        pdc.set(key, PersistentDataType.LONG, cooldown)
    }

    override fun isOnCooldown(player: Player, trade: SurfTrade): Boolean {
        return this.getCooldown(player, trade) > System.currentTimeMillis()
    }
}