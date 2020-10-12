package streamamgapp.dependencyinjection

import dagger.Module
import dagger.Provides
import streamamgapp.network.ApiClient
import streamamgapp.network.ApiClientBuilder
import streamamgapp.network.interactors.VideosNetworkInteractor
import javax.inject.Singleton

@Module
class FeedModule {

    @Provides
    @Singleton
    fun providesApiClient(): ApiClient {
        return ApiClientBuilder.apiClient()
    }

    @Provides
    @Singleton
    fun providesVideoNetworkInteractor(apiClient: ApiClient): VideosNetworkInteractor {
        return VideosNetworkInteractor(apiClient)
    }
}