package dev.slne.surf.trader.api.trader


import net.kyori.adventure.text.Component
import org.bukkit.Location

import java.util.UUID

data class SurfTrader (
    val id: UUID,
    val name: String,
    val displayName: Component,
) {
    fun spawn(location: Location) {

    }

    fun despawn() {

    }
}