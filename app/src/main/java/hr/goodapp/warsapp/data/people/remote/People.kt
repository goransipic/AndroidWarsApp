package hr.goodapp.warsapp.data.people.remote


import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<Result>?
)