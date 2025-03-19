package dev.slne.surf.trader.core.service

import dev.slne.surf.surfapi.core.api.util.requiredService
import dev.slne.surf.trader.api.requirement.SurfTradeRequirement
import dev.slne.surf.trader.api.trade.SurfTrade
import org.bukkit.entity.Player

interface RequirementService {
    suspend fun hasRequirement(player: Player, requirement: SurfTradeRequirement): Boolean
    suspend fun hasRequirements(player: Player, trade: SurfTrade)

    companion object {
        val INSTANCE = requiredService<RequirementService>()
    }
}

val requirementService get() = RequirementService.INSTANCE