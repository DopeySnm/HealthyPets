package dev.unit6.healthypets.presenter.personalInfo

data class PersonalInfoFieldUi(
    val name: String,
    val hint: String,
    val inputValue: String,
    val funk: (String) -> Unit
)
