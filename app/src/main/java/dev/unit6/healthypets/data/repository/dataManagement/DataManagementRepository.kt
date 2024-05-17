package dev.unit6.healthypets.data.repository.dataManagement

interface DataManagementRepository {
    suspend fun wipeData()
}
