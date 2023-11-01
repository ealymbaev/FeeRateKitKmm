package io.horizontalsystems.feeratekitkmm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketLaunch(
    @SerialName("flight_number")
    val flightNumber: Long,
    @SerialName("name")
    val missionName: String
)
