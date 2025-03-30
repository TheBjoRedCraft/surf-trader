package dev.slne.surf.trader.example

import dev.slne.surf.trader.api.trade.SurfTrade
import dev.slne.surf.trader.api.trader.SurfMenuableTrader
import dev.slne.surf.trader.api.trader.SurfTrader
import it.unimi.dsi.fastutil.objects.ObjectList
import net.kyori.adventure.text.Component
import java.util.*

class SurfExampleTrader (
    override val id: UUID,
    override val name: String,
    override val displayName: Component,
    override val trades: ObjectList<SurfTrade>
): SurfTrader, SurfMenuableTrader {
    override fun openMenu() {
        
    }
}