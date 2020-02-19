package edu.rosehulman.catchandkit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_thumbnail_detail.view.*

private const val ARG_THUMBNAIL = "ARG_THUMBNAIL"

class ThumbnailDetailFragment : Fragment() {
    private var thumbnail: Thumbnail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            thumbnail = it.getParcelable(ARG_THUMBNAIL)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(thumbnail: Thumbnail) =
            ThumbnailDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_THUMBNAIL, thumbnail)
                }
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_thumbnail_detail, container, false)
        view.detail_text_view.text = thumbnail?.label
        Picasso.get()
            .load(thumbnail!!.url)
            .into(view.detail_image_view)
        return view
    }
}
