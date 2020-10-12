package streamamgapp.activities

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import development.dreamcatcher.newslightapp.features.feed.VerticalListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.loading_badge.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import streamamgapp.R
import streamamgapp.dependencyinjection.StreamAMGAndroidApp
import streamamgapp.models.GetVideosResponse
import streamamgapp.models.Section
import streamamgapp.viewmodels.FeedViewModel
import javax.inject.Inject


// Main ('feed') view
class FeedActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: FeedViewModel
    private val listAdapter: VerticalListAdapter = VerticalListAdapter()

    private val STATE_LOADING_ERROR = "STATE_LOADING_ERROR"
    private val STATE_CONTENT_LOADED = "STATE_CONTENT_LOADED"

    init {
        StreamAMGAndroidApp.mainComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize ViewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FeedViewModel::class.java)

        // Initialize RecyclerView (feed items)
        setupRecyclerView()

        // Fetch feed items from the back-end and load them into the view
        fetchVideos()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        main_feed_recyclerview.layoutManager = layoutManager
        main_feed_recyclerview.adapter = listAdapter
    }

    private fun fetchVideos() {
        viewModel.getVideos().enqueue(object: Callback<GetVideosResponse> {

            override fun onResponse(call: Call<GetVideosResponse>?, response: Response<GetVideosResponse>?) {
                response?.let {
                    if (it.isSuccessful) {
                        it.body()?.sections?.let {
                            loadItemsIntoList(it)
                        }
                        setViewState(STATE_CONTENT_LOADED)
                    } else {
                        setViewState(STATE_LOADING_ERROR)
                    }
                }
            }

            override fun onFailure(call: Call<GetVideosResponse>?, t: Throwable?) {
                setViewState(STATE_LOADING_ERROR)
            }
        })
    }

    private fun loadItemsIntoList(items: List<Section>) {
        listAdapter.setSections(items)
    }

    private fun displayErrorDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.loading_problem_check_the_internet_connection)
        builder.setPositiveButton(R.string.try_again) { _, _ ->
            fetchVideos()
        }
        builder.create().show()
    }

    private fun setViewState(state: String) {
        when(state) {
            STATE_LOADING_ERROR -> setupLoadingErrorView()
            STATE_CONTENT_LOADED -> setupContentLoadedView()
        }
    }

    private fun setupLoadingErrorView() {
        displayErrorDialog()
    }

    private fun setupContentLoadedView() {
        loading_container.visibility = View.GONE
    }

}
