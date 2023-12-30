package ru.woyfit.data.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignupRequest (
    @SerializedName("username") @Expose val username: String,
    @SerializedName("password") @Expose val password: String
)