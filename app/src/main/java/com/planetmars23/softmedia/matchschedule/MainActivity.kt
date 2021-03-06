package com.planetmars23.softmedia.matchschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.planetmars23.softmedia.matchschedule.R.array.league
import com.planetmars23.softmedia.matchschedule.R.color.colorAccent
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivity : AppCompatActivity(), MainView {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var adapter: MainAdapter
    private lateinit var presenter: MainPresenter

    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var listTeam: RecyclerView
    private lateinit var spinner: Spinner

    private lateinit var leagueName: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        adapter = MainAdapter(teams)
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.HORIZONTAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner()

            swipeRefreshLayout = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                )
            }

            relativeLayout {
                lparams(width = matchParent, height = wrapContent)

                listTeam = recyclerView{
                    lparams(width= matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)

                }

                progressBar = progressBar {
                }.lparams {

                  centerHorizontally()

                }

            }
        }

        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                        presenter.getTeamList(leagueName)

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        swipeRefreshLayout.onRefresh {
            presenter.getTeamList(leagueName)
        }
    }

    override fun showLoading() {
        progressBar.visible()

    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Team>) {

        swipeRefreshLayout.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }



}
