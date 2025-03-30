package dev.slne.surf.trader.api.reward

import dev.slne.surf.trader.api.reward.SurfTradeReward
import dev.slne.surf.trader.api.util.VisualTransaction
import dev.slne.surf.transaction.api.transaction.Transaction
import dev.slne.surf.transaction.api.transaction.TransactionResult
import dev.slne.surf.transaction.api.transactionApi
import org.bukkit.entity.Player

class SurfTradeTransactionReward(
    private val transaction: VisualTransaction
): SurfTradeReward {
    override fun getReward(): Any {
        return transaction
    }

    override suspend fun giveReward(player: Player): Boolean {
        return transactionApi.getTransactionUser(player.uniqueId).deposit(transaction.amount, transaction.currency).first == TransactionResult.SUCCESS
    }
}