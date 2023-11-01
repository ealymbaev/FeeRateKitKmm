package io.horizontalsystems.feeratekitkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform