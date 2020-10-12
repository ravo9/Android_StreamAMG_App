package streamamgapp.network

import retrofit2.Call
import retrofit2.http.GET
import streamamgapp.models.GetVideosResponse

// External gate for communication with API endpoints (operated by Retrofit)
interface ApiClient {

    @GET(NetworkConstants.GET_VIDEOS_ENDPOINT_URL)
    fun getVideos(): Call<GetVideosResponse>
}