package com.yotu.command

import cn.nukkit.Player
import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender
import com.yotu.PlayerStatus
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class StatusCommand: Command("status") {
    init {
        description = "ステータスを確認します"
    }

    override fun execute(sender: CommandSender?, commandLabel: String?, args: Array<out String>?): Boolean {
        val logger = sender?.server?.logger
        if(sender !is Player) {
            logger?.alert("コンソールからは実行できません")
            return true
        }

        val uuid = sender.uniqueId
        transaction {
            // プレイヤーデータの呼び出し
            val data = PlayerStatus.select {PlayerStatus.uuid eq uuid}

            val name = sender.name
            val money = data.single()[PlayerStatus.money]
            val exp = data.single()[PlayerStatus.exp]
            val level = data.single()[PlayerStatus.level]
            val breakAmount = data.single()[PlayerStatus.break_amount]

            sender.sendMessage("""
                §2$name：
                  所持金　：$money
                  レベル　：$level（総経験値：$exp）
                  総整地量：$breakAmount
            """.trimIndent())
            }
        return true
    }
}