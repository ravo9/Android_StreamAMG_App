package development.dreamcatcher.newslightapp.features.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.section.view.*
import streamamgapp.R
import streamamgapp.models.Section

// Main adapter used for managing main feed list within the main Recycler View
class VerticalListAdapter : RecyclerView.Adapter<VerticalListAdapter.ViewHolder>() {

    private var sectionsList: List<Section> = ArrayList()
    private var context: Context? = null

    fun setSections(sections: List<Section>) {
        this.sectionsList = sections
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return sectionsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val sectionView = inflater.inflate(R.layout.section, parent, false)
        return SectionViewHolder(sectionView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Prepare fetched data
        val videos = sectionsList[position].itemData
        val header = sectionsList[position].name

        // Set adapter and layout manager to the internal RecyclerView
        (holder as? SectionViewHolder)?.recyclerView?.let {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val adapter = HorizontalListAdapter(videos, context!!)
            it.adapter = adapter
        }

        // Set the header
        (holder as? SectionViewHolder)?.header?.text = header
    }

    abstract class ViewHolder (view: View) : RecyclerView.ViewHolder(view)

    inner class SectionViewHolder (view: View) : ViewHolder(view) {
        val recyclerView = view.recyclerView
        val header = view.textView_header
    }
}