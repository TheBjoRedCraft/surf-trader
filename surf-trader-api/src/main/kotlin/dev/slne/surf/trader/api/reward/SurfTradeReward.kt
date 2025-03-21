package dev.slne.surf.trader.api.reward

import org.bukkit.entity.Player

interface SurfTradeReward {
    fun getReward(): Any
    suspend fun giveReward(player: Player): Boolean
}