package streamamgapp.features.detailedview

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import streamamgapp.data.database.PostDatabaseEntity
import streamamgapp.data.repositories.PostsRepository
import javax.inject.Inject

class DetailedViewViewModel @Inject constructor(private val postsRepository: PostsRepository)
    : ViewModel(), LifecycleObserver {

    fun getSingleSavedPostById(id: Int): LiveData<PostDatabaseEntity>? {
        return postsRepository.getSingleSavedPostById(id)
    }
}