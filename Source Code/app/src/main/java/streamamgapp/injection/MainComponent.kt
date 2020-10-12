package streamamgapp.injection

import dagger.Component
import streamamgapp.data.database.PostsDatabaseInteractor
import streamamgapp.data.network.PostsNetworkInteractor
import streamamgapp.features.detailedview.DetailedViewFragment
import streamamgapp.features.detailedview.DetailedViewViewModel
import streamamgapp.features.feed.FeedActivity
import streamamgapp.features.feed.FeedViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, FeedModule::class, ViewModelModule::class))
interface MainComponent {
    fun inject(feedActivity: FeedActivity)
    fun inject(detailedViewFragment: DetailedViewFragment)
    fun inject(feedViewModel: FeedViewModel)
    fun inject(detailedViewViewModel: DetailedViewViewModel)
    fun inject(postsNetworkInteractor: PostsNetworkInteractor)
    fun inject(postsDatabaseInteractor: PostsDatabaseInteractor)
}