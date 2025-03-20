package dev.slne.surf.trader.core.service.requirement

import dev.slne.surf.trader.api.requirement.SurfTradeRequirement
import dev.slne.surf.transaction.api.TransactionApi
import dev.slne.surf.transaction.api.transaction.Transaction
import dev.slne.surf.transaction.api.transaction.TransactionResult
import dev.slne.surf.transaction.api.transaction.TransactionResultType
import dev.slne.surf.transaction.api.transactionApi
import org.bukkit.entity.Player

class SurfTradeTransactionRequirement(
    private val transaction: Transaction
): SurfTradeRequirement {
    override fun getRequirement(): Any {
        return transaction
    }

    override suspend fun removeRequirements(player: Player): Boolean {
        return transactionApi.getTransactionUser(player.uniqueId).withdraw(transaction.amount, transaction.currency).first == TransactionResult.SUCCESS
    }
}