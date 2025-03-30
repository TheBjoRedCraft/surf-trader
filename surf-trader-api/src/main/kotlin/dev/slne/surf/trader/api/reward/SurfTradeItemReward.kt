package dev.slne.surf.trader.api.reward

import dev.slne.surf.trader.api.reward.SurfTradeReward
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class SurfTradeItemReward(
    private val item: ItemStack
): SurfTradeReward {
    override fun getReward(): Any {
        return item
    }

    override suspend fun giveReward(player: Player): Boolean {
        val inventory = player.inventory
        val emptySlot = inventory.firstEmpty()

        return if (emptySlot != -1) {
            inventory.setItem(emptySlot, item)
            true
        } else {
            player.world.dropItem(player.location, item).owner = player.uniqueId
            true
        }
    }
}