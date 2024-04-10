package dev.unit6.healthypets.data.preference

interface PreferenceProvider {
    fun setFirstRun()

    fun getFirstRun(): Boolean
}
