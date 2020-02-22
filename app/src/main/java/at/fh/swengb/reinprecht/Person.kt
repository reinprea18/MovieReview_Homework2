package at.fh.swengb.reinprecht

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

class Person(val name: String, val profileImagePath: String) {
}