package com.yotu

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerJoinEvent
import cn.nukkit.plugin.PluginBase
import com.yotu.command.StatusCommand
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class Main: PluginBase(), Listener {
    override fun onEnable() {
        Database.connect("jdbc:sqlite:\\$dataFolder\\data\\data", "org.sqlite.JDBC")
        transaction {
            SchemaUtils.create(PlayerStatus)
        }

        this.server.pluginManager.registerEvents(this, this)
        this.server.commandMap.registerAll(
            "status", listOf(StatusCommand())
        )
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        if(!player.hasPlayedBefore()) return
        transaction {
            PlayerStatus.insert {
                it[uuid] = player.uniqueId
                it[name] = player.name
                it[money] = 0
                it[level] = 1
                it[exp] = 0
                it[break_amount] = 0
            }
        }
    }
}