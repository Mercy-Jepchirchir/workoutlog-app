package dev.Chirii.workoutlog.models

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("first_name")var firstName : String,
    @SerializedName("last_name")var lastName : String,
    @SerializedName("email")var email : String,
    @SerializedName("password")var phoneNumber : String,
    @SerializedName("phone_number")var Password : String,
)
