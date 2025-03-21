package dev.slne.surf.trader.bukkit.implementation

import com.google.auto.service.AutoService
import dev.slne.surf.trader.api.result.TradeResult
import dev.slne.surf.trader.api.trade.SurfTrade
import dev.slne.surf.trader.core.service.*
import net.kyori.adventure.util.Services.Fallback
import org.bukkit.entity.Player

@AutoService(TradeService::class)
class BukkitTradeService(): TradeService, Fallback {
    override suspend fun trade(player: Player, trade: SurfTrade): TradeResult {
        if (cooldownService.isOnCooldown(player, trade)) {
            return TradeResult.FAILURE_COOLDOWN
        }

        if(!requirementService.removeRequirements(player, trade)) {
            return TradeResult.FAILURE_REQUIREMENTS
        }

        return if(rewardService.giveRewards(player, trade)) {
            cooldownService.setCooldown(player, trade, System.currentTimeMillis() + trade.cooldown)
            TradeResult.SUCCESS
        } else {
            TradeResult.FAILURE
        }
    }
}