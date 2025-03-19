package dev.slne.surf.trader.bukkit.implementation

import com.google.auto.service.AutoService
import dev.slne.surf.trader.api.requirement.SurfTradeRequirement
import dev.slne.surf.trader.api.trade.SurfTrade
import dev.slne.surf.trader.core.service.RequirementService
import net.kyori.adventure.util.Services.Fallback
import org.bukkit.entity.Player

@AutoService(RequirementService::class)
class BukkitRequirementService(): RequirementService, Fallback {
    override suspend fun hasRequirement(player: Player, requirement: SurfTradeRequirement): Boolean {
        return requirement.hasRequirement(player)
    }

    override suspend fun hasRequirements(player: Player, trade: SurfTrade) {
        TODO("Not yet implemented")
    }
}