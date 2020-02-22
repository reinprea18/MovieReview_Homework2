package at.fh.swengb.reinprecht

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        val ADD_OR_EDIT_RATING_REQUEST = 1
    }

    val movieAdapter = MovieAdapter() {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_MOVIE_ID, it.id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadContent()
        movie_recycler_view.layoutManager = GridLayoutManager(this,3) as RecyclerView.LayoutManager?
        movie_recycler_view.adapter = movieAdapter


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_OR_EDIT_RATING_REQUEST && resultCode == Activity.RESULT_OK) {
            loadContent()
        }
    }

    private fun loadContent() {
        MovieRepository.moviesList(
            success = {
                // handle success
                movieAdapter.updateList(it)
            },
            error = {
                // handle error
                Log.e("API Call Error", it)
            }
        )
    }

    /*
    fun parseJson(){

        val json = """
            {
             "id": "278",
             "title": "The Shawshank Redemption",
             "posterImagePath": "https://image.tmdb.org/t/p/w200/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg",
             "backdropImagePath": "https://image.tmdb.org/t/p/w500/j9XKiZrVeViAixVRzCta7h1VU9W.jpg",
             "release": "1994-09-23",
             "reviews": [
                 {
                     "reviewValue": 4.5,
                     "reviewText": "Great Movie"
                 }
             ]
             }
     """

     val moshi = Moshi.Builder().build()
     val jsonAdapter = moshi.adapter<Movie>(Movie::class.java)
     val result = jsonAdapter.fromJson(json)
        Log.i("JSON", result.toString())
 } */



}
