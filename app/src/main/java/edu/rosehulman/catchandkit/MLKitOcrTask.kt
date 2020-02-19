package edu.rosehulman.catchandkit

import android.app.usage.ConfigurationStats
import android.graphics.Bitmap
import android.util.Log
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentText


class MLKitOcrTask : MLKitTask {

    override fun execute(downloadUri: String, bitmap: Bitmap, thumbnailRef: CollectionReference) {
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        val detector = FirebaseVision.getInstance().onDeviceTextRecognizer
        detector.processImage(image)
            .addOnSuccessListener { result ->
                Log.d(Constants.TAG, "Text recognized: ${result.text}")
                thumbnailRef.add(Thumbnail(downloadUri, result.text))
            }
            .addOnFailureListener { e ->
                Log.d(Constants.TAG, "Failure recognizing text")
            }
    }
}
