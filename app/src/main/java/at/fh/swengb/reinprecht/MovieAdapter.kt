package at.fh.swengb.reinprecht

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.item_movie.view.item_movie_title

class MovieAdapter(val clickListener: (movie: Movie) -> Unit): RecyclerView.Adapter<MovieViewHolder>() {

    fun updateList(newList: List<Movie>) {
        movieList = newList
        notifyDataSetChanged()
    }

    private var movieList = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val movieItem = inflater.inflate(R.layout.item_movie, parent,false)
        return MovieViewHolder(movieItem, clickListener)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binItem(movie)
    }
}

class MovieViewHolder(itemView: View, val clickListener: (movie: Movie) -> Unit): RecyclerView.ViewHolder(itemView) {
    fun binItem(movie: Movie) {
        itemView.item_movie_title.text = movie.title
        Glide
            .with(itemView)
            .load(movie.posterImagePath)
            .into(itemView.item_movie_poster)


        itemView.setOnClickListener {
            clickListener(movie)
        }
    }

}