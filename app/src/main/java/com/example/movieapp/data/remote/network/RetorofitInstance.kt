import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private const val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkNjQ3ZTFjNTY5MWZiZWMzOTU0MmVjZWUyMDA5ZDQxNSIsIm5iZiI6MTc4NTYwOTU4NC42OCwic3ViIjoiNmE2ZTNkNzBmMjEzMWFjNWJiNTQ0MGE4Iiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.UaOA-cDUBK6kiGlrzvQj4kuKLisYHHhzFqpKM5ZQJ5o"

    private val authInterceptor = Interceptor { chain ->

        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $TOKEN")
            .addHeader("accept", "application/json")
            .build()

        chain.proceed(request)
    }


    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()


    val api: MovieApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()
        .create(MovieApi::class.java)
}