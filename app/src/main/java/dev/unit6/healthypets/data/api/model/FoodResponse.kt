package dev.unit6.healthypets.data.api.model

import dev.unit6.healthypets.data.model.Food

data class FoodResponse(
    val brand: BrandResponse,
    val calcium: Double,
    val calorie: Double,
    val carbohydrates: List<CarbohydrateResponse>,
    val countryName: String,
    val de: Double,
    val description: String,
    val diseases: List<DiseaseResponse>,
    val dry: Boolean,
    val dryCarbon: Double,
    val dryCellulose: Double,
    val dryFat: Double,
    val dryProtein: Double,
    val energyEfficiency: Double,
    val extraSubstances: String,
    val gallery: List<String>,
    val ge: Double,
    val grainless: Boolean,
    val group: BrandGroupResponse,
    val growthPhases: List<String>,
    val humid: Double,
    val id: Int,
    val image: String,
    val ingredientsDescription: String,
    val key: String,
    val me: Double,
    val name: String,
    val nfe: Double,
    val omega3: Double,
    val petActivityLevels: List<String>,
    val petBreeds: List<PetBreedResponse>,
    val petFur: List<String>,
    val petSizes: List<String>,
    val petType: String,
    val phosphorus: Double,
    val proteins: List<ProteinResponse>,
    val sex: Boolean,
    val specialDiets: List<SpecialDietResponse>,
    val vetDiet: Boolean
){
    fun foodResponseToFood(urlImage: String?): Food {
        val nutrients = "Белки ${this.dryProtein}%, " +
                "жиры ${this.dryFat}%, " +
                "минеральные вещества ${this.dryCarbon}%," +
                "клечатка пищевая ${this.dryCellulose}%."

        val typeProtein = this.proteins.joinToString(", ") { it.name } + "."
        val ageRange = this.growthPhases.joinToString(separator = ", ") {
            when(it) {
                "CUB" -> "Молодой"
                "CUB_1" -> "Молодой 1"
                "CUB_2" -> "Молодой 2"
                "ADULT" -> "Взрослый"
                "SENIOR" -> "Пожилой"
                "SENIOR_1" -> "Пожилой 1"
                "SENIOR_2" -> "Пожилой 2"
                else -> ""
            }
        }
        val weightRange = this.petSizes.joinToString(separator = ", ") {
            when(it) {
                "SMALL_X" -> "Мелкие породы"
                "SMALL" -> "Маленькие породы"
                "MEDIUM" -> "Средние породы"
                "MAXI" -> "Макси породы"
                "GIANT" -> "Гигнтские породы"
                "ANY" -> "Любые"
                else -> ""
            }
        }

        val type = if (this.dry) "Сухой" else "-"

        val specialNeeds = this.specialDiets.joinToString(", ") { it.name } + "."

        val composition = this.ingredientsDescription ?: "-"

        val minerals = this.extraSubstances ?: "-"

        val countryName = this.countryName ?: "-"

        return Food(
            id = this.id,
            name = this.name,
            type = type,
            nutrients = nutrients,
            typeProtein = typeProtein,
            ageRange = ageRange,
            weightRange = weightRange,
            countryName = countryName,
            composition = composition,
            minerals = minerals,
            specialNeeds = specialNeeds,
            urlImage = urlImage
        )
    }
}

data class BrandGroupResponse(
    val brandKey: String,
    val id: Int,
    val key: String,
    val name: String
)

data class BrandResponse(
    val groups: List<BrandGroupResponse>,
    val id: Int,
    val key: String,
    val name: String
)

data class CarbohydrateResponse(
    val id: Int,
    val key: String,
    val name: String,
    val type: String
)

data class DiseaseResponse(
    val id: Int,
    val key: String,
    val name: String
)

data class PetBreedResponse(
    val id: Int,
    val key: String,
    val name: String,
    val petSize: String,
    val petType: String
)

data class ProteinResponse(
    val id: Int,
    val key: String,
    val name: String,
    val type: String
)

data class SpecialDietResponse(
    val id: Int,
    val key: String,
    val name: String
)
