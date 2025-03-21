package dev.slne.surf.trader.core.service

import dev.slne.surf.surfapi.core.api.util.requiredService
import dev.slne.surf.trader.api.reward.SurfTradeReward
import dev.slne.surf.trader.api.trade.SurfTrade
import org.bukkit.entity.Player

interface RewardService {
    suspend fun giveReward(player: Player, requirement: SurfTradeReward): Boolean
    suspend fun giveRewards(player: Player, trade: SurfTrade): Boolean

    companion object {
        val INSTANCE = requiredService<RewardService>()
    }
}

val rewardService get() = RewardService.INSTANCE