package com.planetmars23.softmedia.matchschedule

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.planetmars23.softmedia.matchschedule.R.id.team_badge
import com.planetmars23.softmedia.matchschedule.R.id.team_name
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*


class MainAdapter (private val teams: List<Team>) : RecyclerView.Adapter<TeamViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = teams.size


    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {

        holder.BindItem(teams[position])

    }

}

class TeamUI: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){

            linearLayout{
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.HORIZONTAL
                padding = dip(16)

                imageView {
                    id = R.id.team_badge
                }.lparams {
                    width = dip(50)
                    height = dip(50)
                }

                textView {
                    id = R.id.team_name
                    textSize = 16f
                }.lparams{
                    margin = 18

            }
            }

        }
    }
}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view)
{
    private val teamBadge: ImageView = view.find(team_badge)
    private val txtNama: TextView = view.find(team_name)

    fun BindItem(teams: Team)
    {
        Picasso.get().load(teams.teamBadge).into(teamBadge)
                txtNama.text = teams.teamName
    }
}


