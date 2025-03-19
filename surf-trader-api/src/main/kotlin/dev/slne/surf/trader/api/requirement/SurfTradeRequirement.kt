package dev.slne.surf.trader.api.requirement

import org.bukkit.entity.Player

interface SurfTradeRequirement {
    fun getRequirement(): Any
    suspend fun hasRequirement(player: Player): Boolean
}