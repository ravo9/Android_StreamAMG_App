package streamamgapp.features.feed

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import streamamgapp.data.database.PostDatabaseEntity
import streamamgapp.data.repositories.PostsRepository
import javax.inject.Inject

class FeedViewModel @Inject constructor(private val postsRepository: PostsRepository)
    : ViewModel(), LifecycleObserver {

    fun subscribeForPosts(backendUpdateRequired: Boolean): LiveData<List<PostDatabaseEntity>>? {
        return postsRepository.getAllPosts(backendUpdateRequired)
    }

    fun refreshPosts() {
        return postsRepository.refreshPostsWithBackend()
    }

    fun fetchMorePosts(lastPostName: String) {
        return postsRepository.fetchMorePostsWithBackend(lastPostName)
    }

    fun subscribeForUpdateErrors(): LiveData<Boolean>? {
        return postsRepository.subscribeForUpdateErrors()
    }
}