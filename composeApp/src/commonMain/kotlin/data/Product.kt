package data

import kotlinx.serialization.Serializable

@Serializable
data class Product(val image: String = "",
                   val price: Double = 0.0,
                   val rating: Rating? = null,
                   val description: String = "",
                   val id: Int = 0,
                   val title: String = "",
                   val category: String = "")