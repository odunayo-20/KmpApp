package data

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val status: String,
    val message: String,
    val products: List<Product>
)