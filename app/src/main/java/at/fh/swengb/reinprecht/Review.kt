package at.fh.swengb.reinprecht

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

class Review(val reviewValue: Double, val reviewText: String) {
}