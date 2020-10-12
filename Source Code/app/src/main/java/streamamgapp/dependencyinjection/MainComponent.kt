package streamamgapp.dependencyinjection

import dagger.Component
import streamamgapp.activities.FeedActivity
import streamamgapp.viewmodels.FeedViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, FeedModule::class, ViewModelModule::class))
interface MainComponent {
    fun inject(feedActivity: FeedActivity)
    fun inject(feedViewModel: FeedViewModel)
}