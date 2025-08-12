package data

import kotlinx.serialization.Serializable

@Serializable
data class Rating(val rate: Double = 0.0,
                  val count: Int = 0)