package com.gabo.quiz9_securityscren.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabo.quiz9_securityscren.R
import com.gabo.quiz9_securityscren.databinding.NumberItemBinding
import com.gabo.quiz9_securityscren.model.NumberModel

class NumbersAdapter(private val click: (NumberModel) -> Unit) :
    RecyclerView.Adapter<NumbersAdapter.NumberVH>() {
    private var list: List<NumberModel> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<NumberModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class NumberVH(private val binding: NumberItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: NumberModel, click: (NumberModel) -> Unit) {
            with(binding) {
                when (model.icon) {
                    "delete" -> {
                        tvNumber.visibility = View.GONE
                        ivDeleteOrTouchId.visibility = View.VISIBLE
                        ivDeleteOrTouchId.setImageResource(R.drawable.ic_delete)
                    }
                    "touchId" -> {
                        tvNumber.visibility = View.GONE
                        ivDeleteOrTouchId.visibility = View.VISIBLE
                        ivDeleteOrTouchId.setImageResource(R.drawable.ic_touch__id)
                    }
                    else -> {
                        tvNumber.visibility = View.VISIBLE
                        ivDeleteOrTouchId.visibility = View.GONE
                        tvNumber.text = model.icon
                    }
                }
            }
            itemView.setOnClickListener { click(model) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberVH {
        val binding = NumberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberVH(binding)
    }

    override fun onBindViewHolder(holder: NumberVH, position: Int) {
        val item = list[position]
        holder.bind(item, click)
    }

    override fun getItemCount(): Int = list.size
}