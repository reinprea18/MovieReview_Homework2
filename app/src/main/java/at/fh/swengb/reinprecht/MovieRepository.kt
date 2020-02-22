package at.fh.swengb.reinprecht

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Response.success


object MovieRepository {

    fun moviesList(success: (movieList: List<Movie>) -> Unit,
                   error: (errorMessage: String) -> Unit) {
        MovieApi.retrofitService.movies().enqueue(object: Callback<List<Movie>> {
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                error("Call failed")
            }

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    success(responseBody)
                } else {
                    error("Something went wrong")
                }
            }
        })
    }

    fun movieById(id: String,
                  success: (movieDetail: MovieDetail) -> Unit,
                  error: (errorMessage: String) -> Unit) {
        MovieApi.retrofitService.movieDetail(id).enqueue(object: Callback<MovieDetail> {
            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                error("Call failed")
            }

            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    success(responseBody)
                } else {
                    error("Something went wrong")
                }
            }
        })
    }

    fun reviewMovie(id: String, review: Review,
                    success: (message: String) -> Unit,
                    error: (errorMessage: String) -> Unit) {
        MovieApi.retrofitService.reviewMovie(id, review).enqueue(object: Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                error("Unsuccessful call")
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    success("Successful Review")
                } else {
                    error("Something went wrong")
                }
            }
        })
    }
}