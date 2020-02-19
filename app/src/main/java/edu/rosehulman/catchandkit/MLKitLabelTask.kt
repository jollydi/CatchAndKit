package edu.rosehulman.catchandkit

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage

class MLKitLabelTask : MLKitTask {

    override fun execute(urlString: String, bitmap: Bitmap, thumbnailRef: CollectionReference) {
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        val labeler = FirebaseVision.getInstance().onDeviceImageLabeler
        labeler.processImage(image)
            .addOnSuccessListener { result ->
                val labels = result.joinToString {it.text}
                Log.d(Constants.TAG, "Labels recognized: $labels")
                thumbnailRef.add(Thumbnail(urlString, labels))
            }
            .addOnFailureListener { e ->
                Log.d(Constants.TAG, "Failure recognizing labels")
            }
    }

}
