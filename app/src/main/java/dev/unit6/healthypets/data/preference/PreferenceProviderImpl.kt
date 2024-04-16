package dev.unit6.healthypets.data.preference

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceProviderImpl @Inject constructor(
    context: Context
) : PreferenceProvider {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = appContext.getSharedPreferences(sharedPreferenceName, Context.MODE_PRIVATE)


    override fun setIsProtected() {
        preference.edit().putBoolean(
            IS_PROTECTED,
            true
        ).commit() // apply() don't save, therefore use commit
    }

    override fun getIsProtected(): Boolean {
        return preference.getBoolean(IS_PROTECTED, false)
    }

    companion object {
        const val sharedPreferenceName = "healthy_pets_pref"
        const val IS_PROTECTED = "is_protected"
    }
}
