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


    override fun setFirstRun() {
        preference.edit().putBoolean(
            KEY_FIRST_RUN,
            false
        ).commit() // apply() don't save, therefore use commit
    }

    override fun getFirstRun(): Boolean {
        return preference.getBoolean(KEY_FIRST_RUN, true)
    }

    companion object {
        const val sharedPreferenceName = "healthy_pets_pref"
        const val KEY_FIRST_RUN = "first_run"
    }
}
