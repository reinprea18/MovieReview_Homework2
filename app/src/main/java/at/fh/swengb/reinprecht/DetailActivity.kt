package at.fh.swengb.reinprecht

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_review.*
import java.lang.Math.round

class DetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_MOVIE_ID = "MOVIE_ID_EXTRA"
        val REVIEW_MOVIE_REQUEST = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val movieId = intent.getStringExtra(MainActivity.EXTRA_MOVIE_ID)
        displayMovie(movieId)

        button_rate_movie.setOnClickListener {
            val ratingIntent = Intent(this, ReviewActivity::class.java)
            ratingIntent.putExtra(EXTRA_MOVIE_ID,movieId)
            startActivityForResult(ratingIntent, REVIEW_MOVIE_REQUEST)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REVIEW_MOVIE_REQUEST && resultCode == Activity.RESULT_OK) {
            val resultIntent = Intent()
            setResult(Activity.RESULT_OK, resultIntent)
            val movieId = intent.getStringExtra(MainActivity.EXTRA_MOVIE_ID)
            displayMovie(movieId)
        }
    }

    private fun displayMovie(movieId: String?){

        if (movieId != null) {
            MovieRepository.movieById(movieId,
                success = {
                    detail_movie_header.text = it.title
                    detail_movie_director.text = it.director.name
                    detail_movie_actors.text = it.actors.joinToString { it.name }
                    detail_movie_genre.text = it.genres.joinToString(", ")
                    detail_movie_avg_rating_bar.rating = it.ratingAverages().toFloat()
                    detail_movie_avg_review.text = (round(it.ratingAverages() * 10) / 10).toString()
                    detail_movie_review_count.text = it.reviews.count().toString()
                    detail_movie_release.text = it.release
                    detail_movie_plot.text = it.plot

                    Glide
                        .with(this)
                        .load(it.backdropImagePath)
                        .into(detail_movie_backdrop)

                    Glide
                        .with(this)
                        .load(it.posterImagePath)
                        .into(detail_movie_poster)
                },
                error = {
                    Log.e("Something went wrong",it)
                    Toast.makeText(this,it,Toast.LENGTH_LONG).show()
                })
        } else {
            Toast.makeText(this, "Could not find movie", Toast.LENGTH_LONG).show()
            finish()
        }
    }

}