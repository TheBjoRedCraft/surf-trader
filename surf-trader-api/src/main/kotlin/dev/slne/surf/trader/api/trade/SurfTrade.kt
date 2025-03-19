package dev.slne.surf.trader.api.trade

import dev.slne.surf.trader.api.requirement.SurfTradeRequirement
import it.unimi.dsi.fastutil.objects.ObjectList
import net.kyori.adventure.text.Component

data class SurfTrade (
    val id : Int,
    val name: String,
    val displayName: Component,
    val description: Component,

    val cooldown: Long,
    var requirements: ObjectList<SurfTradeRequirement>
)