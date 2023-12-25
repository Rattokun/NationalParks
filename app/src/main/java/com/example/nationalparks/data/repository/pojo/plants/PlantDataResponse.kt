package com.example.nationalparks.data.repository.pojo.plants

import com.example.nationalparks.domain.catalog.items.PlantDataItem
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PlantDataResponse(
    @SerializedName("id") @Expose override val id: String,
    @SerializedName("common_name") @Expose override val commonName: String,
    @SerializedName("scientific_name") @Expose override val scientificName: String,
    @SerializedName("image_url") @Expose override val imageUrl: String
): PlantDataItem