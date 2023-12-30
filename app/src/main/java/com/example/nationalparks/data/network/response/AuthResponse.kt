package com.example.nationalparks.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AuthResponse(@SerializedName("token") @Expose val token: String)