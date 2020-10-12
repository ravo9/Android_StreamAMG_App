package streamamgapp.models

import com.google.gson.annotations.SerializedName

data class Section(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("itemData")
    val itemData: List<Video>?
)