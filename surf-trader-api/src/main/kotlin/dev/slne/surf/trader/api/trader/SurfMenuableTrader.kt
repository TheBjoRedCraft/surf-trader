package dev.slne.surf.trader.api.trader

import org.bukkit.entity.Player

interface SurfMenuableTrader {
    fun openMenu(player: Player)
}