package dev.slne.surf.trader.bukkit

import dev.slne.surf.trader.api.surfTraderApi
import dev.slne.surf.trader.api.trader.SurfTrader
import lol.pyr.znpcsplus.api.NpcApiProvider
import lol.pyr.znpcsplus.api.npc.NpcEntry
import lol.pyr.znpcsplus.util.NpcLocation
import org.bukkit.Location

fun Location.toNpcLocation(): NpcLocation {
    return NpcLocation(
        this.x,
        this.y,
        this.z,
        this.yaw,
        this.pitch
    )
}

fun NpcEntry.toLoadedSurfTrader(): SurfTrader? {
    return surfTraderApi.loadedTraders.find { it.id.toString() == this.id }
}

fun SurfTrader.toLoadedNpcEntry(): NpcEntry {
    return NpcApiProvider.get().npcRegistry.getById(this.id.toString())
}