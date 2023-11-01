package io.horizontalsystems.feeratekitkmm

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllRocketLaunches()
        }
    }

    internal fun getAllLaunches(): List<RocketLaunch> {
        return dbQuery.selectAllRocketLaunchesInfo(::mapLaunchSelecting).executeAsList()
    }

    private fun mapLaunchSelecting(flightNumber: Long, missionName: String): RocketLaunch {
        return RocketLaunch(
            flightNumber = flightNumber,
            missionName = missionName
        )
    }

    internal fun createLaunches(launches: List<RocketLaunch>) {
        dbQuery.transaction {
            launches.forEach { launch ->
                insertLaunch(launch)
            }
        }
    }

    private fun insertLaunch(launch: RocketLaunch) {
        dbQuery.insertRocketLaunch(launch.flightNumber, launch.missionName)
    }
}
