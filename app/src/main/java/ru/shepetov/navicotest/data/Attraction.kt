package ru.shepetov.navicotest.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import ru.shepetov.navicotest.data.Location

@Parcelize
data class Attraction(
    @SerializedName("address")
    val address: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("location")
    val location: Location?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("photo")
    val photo: String?
) : Parcelable