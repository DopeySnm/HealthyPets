package dev.unit6.healthypets.presenter.profile

data class ProfileOptionUi(
    val name: String,
    val action: () -> Unit
)
