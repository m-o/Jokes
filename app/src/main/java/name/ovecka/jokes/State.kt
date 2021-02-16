package name.ovecka.jokes

sealed class State<out T> {
//    object Loading: State<Nothing>()
    class Success<out T>(val data: T): State<T>()
    class Error(val error: String): State<Nothing>()
}