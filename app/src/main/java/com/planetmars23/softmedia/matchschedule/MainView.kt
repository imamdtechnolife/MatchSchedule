package com.planetmars23.softmedia.matchschedule

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}