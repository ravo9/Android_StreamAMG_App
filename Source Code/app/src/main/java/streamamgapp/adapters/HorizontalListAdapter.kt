package development.dreamcatcher.newslightapp.features.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.video.view.*
import streamamgapp.R
import streamamgapp.models.Video
import streamamgapp.utils.TimeFormatter

// Internal adapter providing functionality of horizontally-scriolled list nested within main feed list (recycler view)
class HorizontalListAdapter(list: List<Video>?, private val context: Context) : RecyclerView.Adapter<HorizontalListAdapter.ViewHolder>() {

    private var videosList: List<Video> = ArrayList()

    override fun getItemCount(): Int {
        return videosList.size
    }

    init {
        if (list != null && list.isNotEmpty()) {
            (videosList as ArrayList).addAll(list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val videoView = inflater.inflate(R.layout.video, parent, false)
        return VideoViewHolder(videoView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Prepare ViewHolder
        val viewHolder = holder as VideoViewHolder

        // Prepare fetched data
        val corecategories = videosList[position].metaData?.corecategories?.joinToString(separator = " | ")?.toUpperCase()
        val title = videosList[position].metaData?.title
        val pictureUrl = videosList[position].mediaData?.thumbnailUrl
        val time = TimeFormatter().formatTimeToDisplay(videosList[position].metaData?.videoDuration)

        // Set data within the holder
        viewHolder.corecategories.text = corecategories
        viewHolder.title.text = title
        viewHolder.time.text = time

        // Load thumbnail
        Glide.with(context)
            .load(pictureUrl)
            .into(viewHolder.picture)
    }

    abstract class ViewHolder (view: View) : RecyclerView.ViewHolder(view)

    inner class VideoViewHolder (view: View) : ViewHolder(view) {
        val corecategories = view.textView_corecategories
        val title = view.textView_title
        val time = view.textView_time
        val picture = view.imageView_picture
    }
}