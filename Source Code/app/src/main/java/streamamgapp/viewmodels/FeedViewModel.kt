package streamamgapp.viewmodels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import retrofit2.Call
import streamamgapp.models.GetVideosResponse
import streamamgapp.network.interactors.VideosNetworkInteractor
import javax.inject.Inject

class FeedViewModel @Inject constructor(private val videosNetworkInteractor: VideosNetworkInteractor)
    : ViewModel(), LifecycleObserver {

    fun getVideos(): Call<GetVideosResponse> {
        return videosNetworkInteractor.getVideos()
    }
}