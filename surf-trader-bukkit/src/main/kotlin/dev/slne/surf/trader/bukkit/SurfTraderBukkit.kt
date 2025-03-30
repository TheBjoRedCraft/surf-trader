package dev.slne.surf.trader.bukkit

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surf.trader.bukkit.listener.NpcInteractListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

val plugin get() = JavaPlugin.getPlugin(SurfTraderBukkit::class.java)

class SurfTraderBukkit(): SuspendingJavaPlugin() {
    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(NpcInteractListener(), this)
    }
}