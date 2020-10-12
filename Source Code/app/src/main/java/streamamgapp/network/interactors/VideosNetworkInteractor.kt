package streamamgapp.network.interactors

import retrofit2.Call
import streamamgapp.models.GetVideosResponse
import streamamgapp.network.ApiClient
import javax.inject.Inject

class VideosNetworkInteractor @Inject constructor(private val apiClient: ApiClient) {

    fun getVideos(): Call<GetVideosResponse> {
        return apiClient.getVideos()
    }
}