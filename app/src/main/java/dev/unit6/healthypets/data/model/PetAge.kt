package dev.unit6.healthypets.data.model

import dev.unit6.healthypets.R

enum class PetAge(val stringId: Int) {
    CUB(R.string.CUB),
    CUB_1(R.string.CUB_1),
    CUB_2(R.string.CUB_2),
    ADULT(R.string.ADULT),
    SENIOR(R.string.SENIOR),
    SENIOR_1(R.string.SENIOR_1),
    SENIOR_2(R.string.SENIOR_2),
    NOT_AGE(R.string.empty_value);

    companion object {
        fun transform(age: String): PetAge {
            entries.forEach {
                if (it.name == age){
                    return it
                }
            }
            return NOT_AGE
        }
    }
}
