package edu.rosehulman.catchandkit

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.common.FirebaseVisionImage

class MLKitLandmarkTask : MLKitTask {

    override fun execute(urlString: String, bitmap: Bitmap, thumbnailRef: CollectionReference) {
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        val detector = FirebaseVision.getInstance()
            .visionCloudLandmarkDetector
        detector.detectInImage(image)
            .addOnSuccessListener { landmarks ->
                val landmarkList = landmarks.joinToString { it.landmark }
                Log.d(Constants.TAG, "Landmarks recognized: $landmarks")
                thumbnailRef.add(Thumbnail(urlString, landmarkList))
            }
            .addOnFailureListener { e ->
                Log.d(Constants.TAG, "Failure recognizing landmarks")
            }
    }
}