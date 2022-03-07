package me.dio.simulador.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Match (

    @SerializedName("descricao")
    val description: String,
    @SerializedName("Local")
    val place: Place,
    @SerializedName("mandante")
    val homeTeam: Team,
    @SerializedName("visitante")
    val awayteam: Team
        ):Parcelable