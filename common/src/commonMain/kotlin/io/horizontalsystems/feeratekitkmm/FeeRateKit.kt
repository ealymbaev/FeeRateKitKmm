package io.horizontalsystems.feeratekitkmm

class FeeRateKit(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = MarketApi()

    @Throws(Exception::class)
    suspend fun getTopCoins(forceReload: Boolean): List<Coin> {
        val cachedCoins = database.getAllCoins()
        return if (cachedCoins.isNotEmpty() && !forceReload) {
            cachedCoins
        } else {
            api.getTopCoins().also {
                database.clearDatabase()
                database.createCoins(it)
            }
        }
    }
}
