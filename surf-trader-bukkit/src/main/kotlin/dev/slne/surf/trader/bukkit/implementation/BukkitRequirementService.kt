package dev.slne.surf.trader.bukkit.implementation

import com.google.auto.service.AutoService
import dev.slne.surf.trader.api.requirement.SurfTradeRequirement
import dev.slne.surf.trader.api.trade.SurfTrade
import dev.slne.surf.trader.core.service.RequirementService
import net.kyori.adventure.util.Services.Fallback
import org.bukkit.entity.Player

@AutoService(RequirementService::class)
class BukkitRequirementService(): RequirementService, Fallback {
    override suspend fun removeRequirement(player: Player, requirement: SurfTradeRequirement): Boolean {
        return requirement.removeRequirements(player)
    }

    override suspend fun removeRequirements(player: Player, trade: SurfTrade): Boolean {
        return trade.requirements.all { it.removeRequirements(player) }
    }
}