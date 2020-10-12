package streamamgapp.models

import com.google.gson.annotations.SerializedName

data class GetVideosResponse(
    @SerializedName("sections")
    val sections: List<Section>?
)