package io.horizontalsystems.feeratekitkmm

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Coin(
    val uid: String,
    val name: String,
    val code: String,
    @Contextual val price: BigDecimal
)
