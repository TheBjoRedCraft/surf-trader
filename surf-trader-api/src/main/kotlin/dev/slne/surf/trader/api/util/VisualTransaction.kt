package dev.slne.surf.trader.api.util

import dev.slne.surf.transaction.api.currency.Currency
import java.math.BigDecimal
import java.util.UUID

data class VisualTransaction(
    val amount: BigDecimal,
    val currency: Currency
)