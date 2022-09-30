package com.gabo.quiz9_securityscren.adapter

import com.gabo.quiz9_securityscren.databinding.DotItemBinding
import com.gabo.quiz9_securityscren.model.DotModel
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabo.quiz9_securityscren.R

class DotsAdapter() : RecyclerView.Adapter<DotsAdapter.DotVH>() {
    private var list: List<DotModel> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<DotModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class DotVH(private val binding: DotItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: DotModel) {
            with(binding) {
                if (model.color == "gray") {
                    root.setImageResource(R.drawable.ic_dot_gray)
                } else {
                    root.setImageResource(R.drawable.ic_dot_green)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotVH {
        val binding = DotItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DotVH(binding)
    }

    override fun onBindViewHolder(holder: DotVH, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size
}