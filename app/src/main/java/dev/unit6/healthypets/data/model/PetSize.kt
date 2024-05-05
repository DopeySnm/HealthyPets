package dev.unit6.healthypets.data.model

import dev.unit6.healthypets.R

enum class PetSize(val stringId: Int) {
    SMALL_X(R.string.SMALL_X),
    SMALL(R.string.SMALL),
    MEDIUM(R.string.MEDIUM),
    MAXI(R.string.MAXI),
    GIANT(R.string.GIANT),
    ANY(R.string.ANY),
    NOT_SIZE(R.string.empty_value);

    companion object {
        fun transform(age: String): PetSize {
            PetSize.entries.forEach {
                if (it.name == age){
                    return it
                }
            }
            return NOT_SIZE
        }
    }
}
