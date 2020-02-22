package at.fh.swengb.reinprecht

import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

object MovieApi {
    const val accessToken = "83c9637b-a17d-4ebf-b69c-a1e71d839731"
    val retrofit: Retrofit
    val retrofitService: MovieApiService
    init {
        val moshi = Moshi.Builder().build()
        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://movies.bloder.xyz")
            .build()
        retrofitService = retrofit.create(MovieApiService::class.java)
    }
}
interface MovieApiService {
    @GET("/${MovieApi.accessToken}/movies")
    fun movies(): Call<List<Movie>>
    @GET("/${MovieApi.accessToken}/movies/{id}")
    fun movieDetail(@Path("id") movieId: String): Call<MovieDetail>
    @POST("/${MovieApi.accessToken}/movies/{id}/rate")
    fun reviewMovie(@Path("id") movieId: String, @Body review: Review): Call<Unit>
}