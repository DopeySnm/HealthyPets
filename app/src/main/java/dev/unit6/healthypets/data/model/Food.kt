package dev.unit6.healthypets.data.model

data class Food(
    val id: Int,
    val name: String,
    val urlImage: String?,
    val type: String,
    val composition: String,
    val ageRange: String,
    val weightRange: String,
    val typeProtein: String,
    val specialNeeds: String,
    val minerals: String,
    val nutrients: String,
    val countryName: String
)
