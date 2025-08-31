package dev.cypdashuhn.rooster.db.utility_tables

import dev.cypdashuhn.rooster.db.RoosterDb
import org.jetbrains.exposed.sql.Table

abstract class UtilityTable(table: Table) {
    init {
        RoosterDb.tables += table
    }
}