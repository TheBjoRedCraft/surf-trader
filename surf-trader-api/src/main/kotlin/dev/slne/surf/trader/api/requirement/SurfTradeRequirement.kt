package dev.slne.surf.trader.api.requirement

interface SurfTradeRequirement {
    fun <T> getRequirement(): T
}