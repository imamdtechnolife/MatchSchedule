package com.planetmars23.softmedia.matchschedule

import com.google.gson.Gson
import java.net.URL

class ApiRepository {

    fun doRequest(url: String) : String
    {
        return URL(url).readText()
    }

    object TheSportDBApi {
        fun getTeams(league: String?): String {
            return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l=" + league
        }

        //fun getEvent(): String{
          //  return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/eventsnextleague.php?id=4328"
        //}
    }
}