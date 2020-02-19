package edu.rosehulman.catchandkit

import android.graphics.Bitmap
import com.google.firebase.firestore.CollectionReference

interface MLKitTask {
    fun execute(
        urlString: String,
        bitmap: Bitmap,
        thumbnailRef: CollectionReference
    )
}