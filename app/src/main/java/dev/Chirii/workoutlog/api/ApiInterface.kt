package dev.Chirii.workoutlog.api

import dev.Chirii.workoutlog.models.LoginRequest
import dev.Chirii.workoutlog.models.LoginResponse
import dev.Chirii.workoutlog.models.RegisterRequest
import dev.Chirii.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>

    @POST("/login")
       suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}
