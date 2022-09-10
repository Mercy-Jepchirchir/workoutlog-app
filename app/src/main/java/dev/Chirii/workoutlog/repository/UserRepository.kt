package dev.Chirii.workoutlog.repository

import dev.Chirii.workoutlog.api.ApiClient
import dev.Chirii.workoutlog.api.ApiInterface
import dev.Chirii.workoutlog.models.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser (loginRequest: LoginRequest) = withContext(Dispatchers.IO){
        val response = apiClient.login(loginRequest)
        return@withContext response
    }
}