package at.fh.swengb.reinprecht

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class Movie(val id: String, val title: String, val release: String, val posterImagePath: String, val backdropImagePath: String,
                 val reviews: MutableList<Review>
) {

    fun ratingAverages(): Double{
        var average = reviews.map { it.reviewValue }.average()


        if (average.isNaN()){
            return 0.0 // returning a default value if there is no review
        }
        return  average
    }
}