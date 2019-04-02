package com.adidas.sports.goal.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adidas.sports.goal.R
import com.adidas.sports.goal.vo.GoalModel
import com.adidas.sports.goal.vo.getGoalStr
import com.adidas.sports.goal.vo.getTrophyResId
import com.adidas.sports.goal.vo.getTrophyStr
import kotlinx.android.synthetic.main.recycler_item_my_goal.view.*


class GoalRA(private val context: Context,
             private val models: List<GoalModel>,
             private val listener: (GoalModel)-> Unit
)
    : RecyclerView.Adapter<GoalRA.CourseViewHolder>() {

    override fun getItemCount(): Int {
       return models.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        return holder.onBind(models[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recycler_item_my_goal, parent, false))
    }
    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(model: GoalModel) = with(itemView) {
            titleTV.text = model.title
            desTV.text = model.description
            goalTV.text = model.getGoalStr()
            tropyTV.text = model.getTrophyStr()
            val drawable = context.getDrawable(model.getTrophyResId())?.apply {
                setBounds(0, 0, intrinsicWidth, intrinsicHeight)
                context.getDrawable(model.getTrophyResId())
            }
            tropyTV.setCompoundDrawables(drawable, null, null, null)
            setOnClickListener {
                listener.invoke(model)
            }
        }
    }

}
