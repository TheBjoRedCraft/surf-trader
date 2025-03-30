package dev.slne.surf.trader.example

import dev.slne.surf.surfapi.bukkit.api.builder.buildItem
import dev.slne.surf.surfapi.bukkit.api.builder.displayName
import dev.slne.surf.trader.api.requirement.SurfTradeTransactionRequirement
import dev.slne.surf.trader.api.reward.SurfTradeItemReward
import dev.slne.surf.trader.api.reward.SurfTradeTransactionReward
import dev.slne.surf.trader.api.trade.SurfTrade
import dev.slne.surf.trader.api.trader.SurfMenuableTrader
import dev.slne.surf.trader.api.trader.SurfTrader
import dev.slne.surf.trader.api.util.VisualTransaction
import dev.slne.surf.trader.core.service.reward.SurfTradeItemRequirement
import dev.slne.surf.transaction.api.currency.Currency
import dev.slne.surf.transaction.api.transactionApi
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import it.unimi.dsi.fastutil.objects.ObjectList
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.*

class SurfExampleTrader (
    override val id: UUID,
    override val name: String,
    override val displayName: Component,
    override val trades: ObjectList<SurfTrade>,
    override val skinName: String
): SurfTrader, SurfMenuableTrader {
    override fun openMenu(player: Player) {

    }

    companion object {
        fun getExampleTransactionTrade(uuid: UUID, id: Int, defaultCurrency: Currency): SurfTrade = SurfTrade(
            uuid,
            "example-trade-transaction-$id",
            Component.text("Example Transaction Trade"),
            Component.text("An example trade for transaction trades"),
            100L,
            ObjectArrayList.of(SurfTradeTransactionRequirement(VisualTransaction(20.toBigDecimal(), defaultCurrency))),
            ObjectArrayList.of(SurfTradeItemReward(buildItem(Material.DIAMOND){ displayName(Component.text("Danke für den Einkauf")) }))
        )

        fun getExampleItemTrade(uuid: UUID, id: Int, defaultCurrency: Currency): SurfTrade = SurfTrade(
            uuid,
            "example-trade-item-$id",
            Component.text("Example Item Trade"),
            Component.text("An example trade for item trades"),
            100L,
            ObjectArrayList.of(SurfTradeItemRequirement(buildItem(Material.NETHER_STAR) { amount = 10 })),
            ObjectArrayList.of(SurfTradeTransactionReward(VisualTransaction(32.toBigDecimal(), defaultCurrency)))
        )
    }
}