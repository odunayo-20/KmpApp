package org.example.project

import apiClient.httpClient
import data.ApiResponse
import data.Product
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flow

class HomeRepository {


    suspend fun getProductsApi(): List<Product> {
        val response = httpClient.get( "https://fakestoreapi.in/api/products")
        val apiResponse = response.body<ApiResponse>()
        return apiResponse.products


    }


    fun getProducts() = flow {
        emit(getProductsApi())
    }
}