package dev.unit6.healthypets.data.model

import dev.unit6.healthypets.presenter.mainScreen.FeedUi

data class Food(
    val id: Int,
    val name: String,
    val urlImage: String?,
    val dry: Boolean,
    val composition: String?,
    val ageRange: List<PetAge>,
    val weightRange: List<PetSize>,
    val typeProtein: String?,
    val specialNeeds: String?,
    val minerals: String?,
    val dryProtein: Double,
    val dryFat: Double,
    val dryCarbon: Double,
    val dryCellulose: Double,
    val countryName: String?,
    var like: Boolean = false
) {
    fun toFeedUI(): FeedUi {
        return FeedUi(
            this.id,
            this.name,
            this.urlImage,
            false
        )
    }
}
