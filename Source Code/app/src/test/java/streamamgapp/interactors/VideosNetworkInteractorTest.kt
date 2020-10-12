package streamamgapp.interactors

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.mock.Calls
import streamamgapp.models.*
import streamamgapp.network.ApiClient
import streamamgapp.network.interactors.VideosNetworkInteractor

class VideosNetworkInteractorTest {

    private var videosNetworkInteractor: VideosNetworkInteractor? = null
    private var fakeGetVideosResponseGsonModel: GetVideosResponse? = null

    @Mock
    private val apiClient: ApiClient? = null

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setupTest() {

        // Inject Mocks
        MockitoAnnotations.initMocks(this)

        // Initialize the Interactor
        videosNetworkInteractor = VideosNetworkInteractor(apiClient!!)

        // Prepare fake data
        val title = "fake/video/title"
        val videoDuration = 12345
        val corecategories = listOf("fake/video/corecategory")
        val thumbnailUrl = "fake/thumbnail/url"
        val videoId = "fake/video/id"
        val sectionName = "fake/section/name"
        val sectionId = "fake/section/id"

        // Prepare fake Gson (API) model objects
        val fakeMetaData = MetaData(corecategories, videoDuration, title)
        val fakeMediaData = MediaData(thumbnailUrl)
        val fakeVideo = Video(videoId, fakeMediaData, fakeMetaData)
        val fakeSection = Section(sectionId, sectionName, listOf(fakeVideo))
        fakeGetVideosResponseGsonModel = GetVideosResponse(listOf(fakeSection))
    }

    @Test
    fun fetchAllVideosByVideosNetworkInteractor() {

        // Prepare API response
        val getAllVideosResponse = Calls.response(fakeGetVideosResponseGsonModel!!)

        // Set testing conditions
        Mockito.`when`(apiClient?.getVideos()).thenReturn(getAllVideosResponse)

        // Perform the action
        val receivedGetAllVideosResponse = videosNetworkInteractor?.getVideos()

        // Check results
        Assert.assertSame(getAllVideosResponse, receivedGetAllVideosResponse)

    }
}
