package dev.slne.surf.trader.api.result

class TradeResult(
    val name: String
) {
    companion object {
        val SUCCESS = TradeResult("success")
        val FAILURE_COOLDOWN = TradeResult("failure_cooldown")
        val FAILURE_REQUIREMENTS = TradeResult("failure_requirements")
        val FAILURE = TradeResult("failure")
    }
}