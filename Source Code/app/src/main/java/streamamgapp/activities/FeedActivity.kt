package streamamgapp.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import streamamgapp.R
import streamamgapp.dependencyinjection.StreamAMGAndroidApp
import streamamgapp.models.GetVideosResponse
import streamamgapp.viewmodels.FeedViewModel
import javax.inject.Inject


// Main ('feed') view
class FeedActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: FeedViewModel
//    private lateinit var postsListAdapter: PostsListAdapter

    init {
        StreamAMGAndroidApp.mainComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize ViewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FeedViewModel::class.java)

        // Initialize RecyclerView (feed items)
//        setupRecyclerView()

        // Fetch feed items from the back-end and load them into the view
        subscribeForFeedItems()
    }

//    private fun setupRecyclerView() {
//        val layoutManager = LinearLayoutManager(this)
//        main_feed_recyclerview.layoutManager = layoutManager
//        postsListAdapter = PostsListAdapter(this) { postId: Int ->
//            displayDetailedView(postId)
//        }
//        main_feed_recyclerview.adapter = postsListAdapter
//        main_feed_recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//
//                if (layoutManager.findLastVisibleItemPosition() + 1 == postsListAdapter.itemCount) {
//                    loadMoreItems()
//                }
//            }
//        })
//    }

    private fun subscribeForFeedItems() {
        viewModel.getVideos().enqueue(object: Callback<GetVideosResponse> {

            override fun onResponse(call: Call<GetVideosResponse>?, response: Response<GetVideosResponse>?) {
                Log.d("TESTO", "SUCCESS")
            }

            override fun onFailure(call: Call<GetVideosResponse>?, t: Throwable?) {
                Log.d("TESTO", "FAIL")
            }
        })
    }

//    private fun refreshPostsSubscription() {
//        viewModel.refreshPosts()
//    }

//    private fun setViewState(state: String) {
//        when(state) {
//            STATE_LOADING_ERROR -> setupLoadingErrorView()
//            STATE_CONTENT_LOADED -> setupContentLoadedView()
//        }
//    }

}
