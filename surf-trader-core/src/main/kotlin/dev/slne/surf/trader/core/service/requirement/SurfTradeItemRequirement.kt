package dev.slne.surf.trader.core.service.requirement

import dev.slne.surf.trader.api.requirement.SurfTradeRequirement
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class SurfTradeItemRequirement(
    private val item: ItemStack
): SurfTradeRequirement {
    override fun getRequirement(): Any {
        return item
    }

    override suspend fun removeRequirements(player: Player): Boolean {
        return player.inventory.removeItem(item).isEmpty()
    }
}