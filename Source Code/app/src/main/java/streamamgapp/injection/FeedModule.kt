package streamamgapp.injection

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import streamamgapp.data.database.PostsDatabase
import streamamgapp.data.database.PostsDatabaseInteractor
import streamamgapp.data.network.ApiClient
import streamamgapp.data.network.PostsNetworkInteractor
import streamamgapp.data.network.NetworkAdapter
import streamamgapp.data.repositories.PostsRepository
import javax.inject.Singleton

@Module
class FeedModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Context): PostsDatabase {
        return Room.databaseBuilder(application, PostsDatabase::class.java, "main_database").build()
    }

    @Provides
    @Singleton
    fun providesPostsDatabaseInteractor(postsDatabase: PostsDatabase): PostsDatabaseInteractor {
        return PostsDatabaseInteractor(postsDatabase)
    }

    @Provides
    @Singleton
    fun providesPostsNetworkInteractor(apiClient: ApiClient): PostsNetworkInteractor {
        return PostsNetworkInteractor(apiClient)
    }

    @Provides
    @Singleton
    fun providesApiClient(): ApiClient {
        return NetworkAdapter.apiClient()
    }

    @Provides
    @Singleton
    fun providesPostsRepository(postsNetworkInteractor: PostsNetworkInteractor,
                               postsDatabaseInteractor: PostsDatabaseInteractor
    ): PostsRepository {
        return PostsRepository(postsNetworkInteractor, postsDatabaseInteractor)
    }
}