package com.example.nationalparks.data.repository.pojo.plants

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PlantData(
    @SerializedName("data") @Expose val data: List<PlantDataResponse>
) {

}