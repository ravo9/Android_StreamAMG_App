package streamamgapp.models

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id")
    val id: String?,

    @SerializedName("mediaData")
    val mediaData: MediaData?,

    @SerializedName("metaData")
    val metaData: MetaData?
)

data class MediaData(
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?
)

data class MetaData(
    @SerializedName("corecategories")
    val corecategories: List<String>?,

    @SerializedName("VideoDuration")
    val videoDuration: Int?,

    @SerializedName("title")
    val title: String?
)