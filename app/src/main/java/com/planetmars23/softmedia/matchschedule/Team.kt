package com.planetmars23.softmedia.matchschedule

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("idTeam")
        val teamId: String? = null,

        @SerializedName("strName")
        val teamName: String? = null,

        @SerializedName("strTeamBadge")
        val teamBadge: String? = null
)