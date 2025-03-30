package dev.slne.surf.trader.bukkit.implementation

import com.google.auto.service.AutoService
import dev.slne.surf.trader.api.SurfTraderApi
import dev.slne.surf.trader.api.result.TradeResult
import dev.slne.surf.trader.api.trade.SurfTrade
import dev.slne.surf.trader.api.trader.SurfTrader
import dev.slne.surf.trader.bukkit.toLoadedNpcEntry
import dev.slne.surf.trader.bukkit.toNpcLocation
import dev.slne.surf.trader.core.service.cooldownService
import dev.slne.surf.trader.core.service.tradeService
import it.unimi.dsi.fastutil.objects.ObjectArraySet
import it.unimi.dsi.fastutil.objects.ObjectSet
import lol.pyr.znpcsplus.api.NpcApiProvider
import lol.pyr.znpcsplus.api.entity.EntityProperty
import lol.pyr.znpcsplus.api.skin.SkinDescriptor
import lol.pyr.znpcsplus.util.LookType
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.kyori.adventure.util.Services.Fallback
import org.bukkit.Location
import org.bukkit.entity.Player

@AutoService(SurfTraderApi::class)
class BukkitSurfTraderApi(): SurfTraderApi, Fallback {
    override val loadedTraders: ObjectSet<SurfTrader> = ObjectArraySet()

    private val npcApi = NpcApiProvider.get()
    private val playerType = npcApi.npcTypeRegistry.getByName("player")

    private val skinProperty: EntityProperty<SkinDescriptor> = npcApi.propertyRegistry.getByName("skin", SkinDescriptor::class.java)
    private val lookProperty: EntityProperty<LookType> = npcApi.propertyRegistry.getByName("look", LookType::class.java)

    override fun getCooldown(player: Player, trade: SurfTrade): Long {
        return cooldownService.getCooldown(player, trade)
    }

    override fun setCooldown(player: Player, trade: SurfTrade, cooldown: Long) {
        cooldownService.setCooldown(player, trade, cooldown)
    }

    override fun isOnCooldown(player: Player, trade: SurfTrade): Boolean {
        return cooldownService.isOnCooldown(player, trade)
    }

    override fun spawnTrader(surfTrader: SurfTrader, location: Location) {
        loadedTraders.add(surfTrader)

        val npcEntry = npcApi.npcRegistry.create(
            surfTrader.id.toString(),
            location.world,
            playerType,
            location.toNpcLocation()
        )

        npcEntry.isProcessed = true
        npcEntry.isSave = true
        npcEntry.isAllowCommandModification = false

        npcEntry.npc.hologram.insertLine(0, PlainTextComponentSerializer.plainText().serialize(surfTrader.displayName))
        npcEntry.npc.setProperty(lookProperty, LookType.PER_PLAYER)
        npcEntry.npc.setProperty(skinProperty, npcApi.skinDescriptorFactory.createStaticDescriptor(surfTrader.skinName))

        npcEntry.enableEverything()
    }

    override fun despawnTrader(surfTrader: SurfTrader) {
        loadedTraders.remove(surfTrader)

        val npcEntry = surfTrader.toLoadedNpcEntry() ?: return

        npcApi.npcRegistry.delete(npcEntry.id)
    }

    override suspend fun trade(player: Player, trade: SurfTrade): TradeResult {
        return tradeService.trade(player, trade)
    }
}