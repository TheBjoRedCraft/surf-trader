package dev.slne.surf.trader.example

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surf.trader.example.command.SurfTraderExampleCommand
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

val plugin get() = JavaPlugin.getPlugin(SurfTraderExample::class.java)

class SurfTraderExample(): SuspendingJavaPlugin() {
    override fun onEnable() {
        SurfTraderExampleCommand("surftraderexample").register()
    }
}