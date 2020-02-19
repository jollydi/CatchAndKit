package edu.rosehulman.catchandkit

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail(val url: String = "", val label: String = "") : Parcelable {

    @IgnoredOnParcel
    @get:Exclude
    var id = ""

    companion object {
        fun fromSnapshot(snapshot: DocumentSnapshot): Thumbnail {
            val thumbnail = snapshot.toObject(Thumbnail::class.java)!!
            thumbnail.id = snapshot.id
            return thumbnail
        }
    }
}