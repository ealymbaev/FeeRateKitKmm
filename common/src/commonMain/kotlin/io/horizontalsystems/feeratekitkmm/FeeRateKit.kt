package io.horizontalsystems.feeratekitkmm

class FeeRateKit {
    private val spaceXApi = SpaceXApi()

    @Throws(Exception::class)
    suspend fun getLaunches(): List<RocketLaunch> {
        return spaceXApi.getAllLaunches()
    }
}
