package dev.Chirii.workoutlog.models

data class RegisterRequest(
    var firstName : String,
    var lastName : String,
    var email : String,
    var phoneNumber : String,
    var Password : String,
)
