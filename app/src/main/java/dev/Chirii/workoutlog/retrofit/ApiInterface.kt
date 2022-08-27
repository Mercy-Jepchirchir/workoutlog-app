package dev.Chirii.workoutlog.retrofit

import dev.Chirii.workoutlog.models.RegisterRequest
import dev.Chirii.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Call<RegisterResponse>
}