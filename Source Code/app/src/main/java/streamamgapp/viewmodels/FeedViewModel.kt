package streamamgapp.viewmodels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import retrofit2.Call
import streamamgapp.models.GetVideosResponse
import streamamgapp.network.ApiClient
import javax.inject.Inject

class FeedViewModel @Inject constructor(private val apiClient: ApiClient)
    : ViewModel(), LifecycleObserver {

    fun getVideos(): Call<GetVideosResponse> {
        return apiClient.getVideos()
    }
}