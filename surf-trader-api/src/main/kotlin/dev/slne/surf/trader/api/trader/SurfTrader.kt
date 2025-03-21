package dev.slne.surf.trader.api.trader

import dev.slne.surf.trader.api.trade.SurfTrade
import it.unimi.dsi.fastutil.objects.ObjectList
import net.kyori.adventure.text.Component

import java.util.UUID

interface SurfTrader {
    val id: UUID
    val name: String
    val displayName: Component

    val trades: ObjectList<SurfTrade>
}