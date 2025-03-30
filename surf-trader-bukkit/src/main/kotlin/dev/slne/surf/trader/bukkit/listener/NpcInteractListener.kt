package dev.slne.surf.trader.bukkit.listener

import dev.slne.surf.trader.api.trader.SurfMenuableTrader
import dev.slne.surf.trader.bukkit.toLoadedSurfTrader
import lol.pyr.znpcsplus.api.event.NpcInteractEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class NpcInteractListener(): Listener {
    @EventHandler
    fun onNpcInteract(event: NpcInteractEvent) {
        val trader = event.entry.toLoadedSurfTrader() ?: return

        if(trader is SurfMenuableTrader) {
            trader.openMenu(event.player)
        }
    }
}