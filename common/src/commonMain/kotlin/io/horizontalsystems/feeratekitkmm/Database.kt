package io.horizontalsystems.feeratekitkmm

import com.ionspin.kotlin.bignum.decimal.toBigDecimal

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllCoins()
        }
    }

    internal fun getAllCoins(): List<Coin> {
        return dbQuery.getAllCoins(::mapCoin).executeAsList()
    }

    private fun mapCoin(uid: String, name: String, code: String, price: String): Coin {
        return Coin(uid, name, code, price.toBigDecimal())
    }

    internal fun createCoins(coins: List<Coin>) {
        dbQuery.transaction {
            coins.forEach { coin ->
                insertCoin(coin)
            }
        }
    }

    private fun insertCoin(coin: Coin) {
        dbQuery.insertCoin(coin.uid, coin.name, coin.code, coin.price.toStringExpanded())
    }
}
