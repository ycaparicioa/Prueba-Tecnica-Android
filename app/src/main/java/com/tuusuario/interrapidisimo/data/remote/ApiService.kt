import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("apicontrollerpruebas/api/Parametros Framework/VPStoreAppControl")
    suspend fun getAppVersion(): Response<VersionResponse> //
    @POST("FtEntregaElectronica/MultiCanales/ApiSeguridad Pruebas/api/Seguridad/AuthenticaUsuarioApp")
    suspend fun login(
        @Header("Usuario") userHeader: String,        // "pam.meredy21"
        @Header("Password") passHeader: String,       // "#7-45"
        @Body loginRequest: LoginBody                 // JSON con credenciales
    ): Response<LoginResponse>

    @GET("apicontrollerpruebas/api/SincronizadorDatos/ObtenerEsquema/true")
    suspend fun getSchemaTables(): Response<List<SchemaDto>> //
    @GET("apicontrollerpruebas/api/Parametros Framework/ObtenerLocalidades Recogidas")
    suspend fun getLocalidades(): Response<List<LocalidadDto>> //
}
