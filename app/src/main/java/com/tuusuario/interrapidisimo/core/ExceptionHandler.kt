import retrofit2.Response
import java.net.UnknownHostException
import java.net.ConnectException

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}

object ExceptionHandler {
    fun <T> handleApiResponse(response: Response<T>): Resource<T> {
        return try {
            if (response.isSuccessful && response.code() == 200) {
                response.body()?.let {
                    Resource.Success(it)
                } ?: Resource.Error("La respuesta del servidor está vacía")
            } else {
                // Punto 1.30: Generar mensaje de alerta si el código es diferente de 200
                Resource.Error("Problema detectado: Error ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            handleException(e)
        }
    }

    fun <T> handleException(e: Exception): Resource<T> {
        return when (e) {
            is UnknownHostException -> Resource.Error("Sin conexión a internet. Verifique su red.")
            is ConnectException -> Resource.Error("No se pudo conectar al servidor de Interrapidisimo.")
            else -> Resource.Error("Error inesperado: ${e.localizedMessage ?: "Consulte al administrador."}")
        }
    }
}
