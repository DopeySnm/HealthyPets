package dev.unit6.healthypets.data.api.model


import dev.unit6.healthypets.data.model.Food
import dev.unit6.healthypets.data.model.PetAge
import dev.unit6.healthypets.data.model.PetSize

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
) {
    fun foodResponseToFood(urlImage: String?): Food {
        val typeProtein = this.proteins.joinToString(", ") { it.name } + "."

        val ageRange = this.growthPhases.map {
            PetAge.transform(it)
        }

        val weightRange = this.petSizes.map {
            PetSize.transform(it)
        }

        val specialNeeds = this.specialDiets.joinToString(", ") { it.name } + "."

        return Food(
            id = this.id,
            name = this.name,
            dry = this.dry,
            dryProtein = this.dryProtein,
            dryFat = this.dryFat,
            dryCarbon = this.dryCarbon,
            dryCellulose = this.dryCellulose,
            typeProtein = typeProtein,
            ageRange = ageRange,
            weightRange = weightRange,
            countryName = this.countryName,
            composition = this.ingredientsDescription,
            minerals = this.extraSubstances,
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
