package com.exercise.mvvmhilttest.presentation.repository_details.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.exercise.mvvmhilttest.databinding.ItemRepositoryInfoBinding
import com.exercise.mvvmhilttest.entity.RepositoryInfoVM

class RepositoryDetailsAdapter :
    RecyclerView.Adapter<RepositoryDetailsAdapter.RepositoryDetailsViewHolder>() {

    private val details = arrayListOf<RepositoryInfoVM>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryDetailsViewHolder {
        val itemBinding = ItemRepositoryInfoBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryDetailsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RepositoryDetailsViewHolder, position: Int) =
        holder.bindView(details[position])

    override fun getItemCount(): Int = details.size

    fun submit(list: List<RepositoryInfoVM>) {
        details.clear()
        details.addAll(list)
        notifyDataSetChanged()
    }

    inner class RepositoryDetailsViewHolder(private val binding: ItemRepositoryInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(data: RepositoryInfoVM): Unit = with(binding) {
            vTitle.text = root.context.resources.getString(data.title)
            vDescription.text = data.description
        }
    }
}