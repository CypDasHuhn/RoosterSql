package dev.cypdashuhn.rooster.db

import com.google.common.cache.CacheBuilder
import dev.cypdashuhn.rooster.common.RoosterCache
import dev.cypdashuhn.rooster.common.RoosterModuleBuilder
import dev.cypdashuhn.rooster.common.RoosterServices
import dev.cypdashuhn.rooster.common.initRooster
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.exposed.sql.Table
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

object RoosterDb {
    internal lateinit var plugin: JavaPlugin
    internal val logger: Logger = Logger.getLogger("RoosterDb")
    lateinit var cache: RoosterCache<String, Any>
    internal var services: RoosterServices = RoosterServices()
    val tables = mutableListOf<Table>()

    fun init(
        plugin: JavaPlugin,
        tables: List<Table>,
        services: RoosterServices? = null,
        cache: RoosterCache<String, Any>? = null
    ) {
        this.plugin = plugin
        if (services != null) this.services = services
        this.cache = cache ?: RoosterCache(
            CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES)
        )

        initDatabase(tables)
        initRooster(plugin, this.services, this.cache)
    }
}

fun RoosterModuleBuilder.db(tables: List<Table>) {
    RoosterDb.init(plugin, tables, services, cache)
}