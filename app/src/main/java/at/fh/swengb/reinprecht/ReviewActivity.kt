package at.fh.swengb.reinprecht

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import at.fh.swengb.reinprecht.MovieRepository.reviewMovie
import kotlinx.android.synthetic.main.activity_review.*

class ReviewActivity : AppCompatActivity() {

    companion object {
        val EXTRA_RETURN_ID_RATING = "RETURN_ID_RATING_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val movieId = intent.getStringExtra(DetailActivity.EXTRA_MOVIE_ID)

        if (movieId == null) {
            finish()

        } else {

            MovieRepository.movieById(movieId,
                success = {movie_rating_header.text = it.title},
                error = {emsg -> Log.e("API_CALL", emsg)})

            rate_movie.setOnClickListener {
                val myMovieRatingObject = Review(
                    movie_rating_bar.rating.toDouble(),
                    movie_feedback.text.toString()
                )

                MovieRepository.reviewMovie( movieId, myMovieRatingObject,
                    success = { smessage -> Log.i("API_CALL", smessage) },
                    error = { emessage -> Log.e("API_CALL", emessage) })

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_RETURN_ID_RATING, movieId)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }

        }
    }


}