package dev.slne.surf.trader.bukkit

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import org.bukkit.plugin.java.JavaPlugin

val plugin get() = JavaPlugin.getPlugin(SurfTraderBukkit::class.java)

class SurfTraderBukkit(): SuspendingJavaPlugin() {

}