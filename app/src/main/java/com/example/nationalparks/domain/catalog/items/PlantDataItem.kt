package com.example.nationalparks.domain.catalog.items

interface PlantDataItem {
    val id: String
    val commonName: String
    val scientificName: String
    val imageUrl: String
}