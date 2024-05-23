package dev.unit6.healthypets.data.preference

interface PreferenceProvider {
    fun setIsProtected()

    fun getIsProtected(): Boolean

    fun clearSharedPreferences()
}
