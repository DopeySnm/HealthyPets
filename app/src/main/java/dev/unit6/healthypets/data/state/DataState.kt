package dev.unit6.healthypets.data.state

sealed class DataState<out T> {
    class Success<T>(val value: T) : DataState<T>()
    class Failure(val message: String) : DataState<Nothing>()
}
