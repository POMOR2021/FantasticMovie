import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") search: String
    ): MovieResponseDto

}