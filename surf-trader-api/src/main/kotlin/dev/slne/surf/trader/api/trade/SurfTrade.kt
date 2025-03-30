package dev.slne.surf.trader.api.trade

import dev.slne.surf.trader.api.requirement.SurfTradeRequirement
import dev.slne.surf.trader.api.reward.SurfTradeReward
import it.unimi.dsi.fastutil.objects.ObjectList
import net.kyori.adventure.text.Component
import org.bukkit.NamespacedKey
import java.util.UUID

data class SurfTrade (
    val id : UUID,
    val name: String,
    val displayName: Component,
    val description: Component,

    val cooldown: Long,
    var requirements: ObjectList<SurfTradeRequirement>,
    var rewards: ObjectList<SurfTradeReward>,

    val cooldownKey: NamespacedKey = NamespacedKey("surf-trader", "trade-cooldown-$id"),
)