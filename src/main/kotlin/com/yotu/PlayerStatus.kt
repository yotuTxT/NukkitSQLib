package com.yotu

import org.jetbrains.exposed.sql.Table

object PlayerStatus: Table("playerstatus") {
    val uuid = uuid("uid")
    val name = varchar("name", 16)
    val money = integer("money")
    val level = integer("level")
    val exp = integer("exp")
    val break_amount = integer("break_amount")
}