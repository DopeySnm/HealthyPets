package dev.unit6.healthypets.data.repository.dataManagement

import dev.unit6.healthypets.data.db.FavoriteFoodDao
import dev.unit6.healthypets.data.db.PinCodeDao
import dev.unit6.healthypets.data.preference.PreferenceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataManagementRepositoryImpl @Inject constructor(
    private val preferenceProvider: PreferenceProvider,
    private val pinCodeDao: PinCodeDao,
    private val favoriteFoodDao: FavoriteFoodDao
) : DataManagementRepository {

    override suspend fun wipeData() {
        withContext(Dispatchers.IO) {
            favoriteFoodDao.deleteAllFavoriteFood()
            pinCodeDao.deleteAllPinCode()
            preferenceProvider.clearSharedPreferences()
        }

    }

}
