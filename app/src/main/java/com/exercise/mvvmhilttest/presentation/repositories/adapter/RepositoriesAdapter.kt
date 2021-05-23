package com.exercise.mvvmhilttest.presentation.repositories.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import com.exercise.domain.entity.Repository
import com.exercise.mvvmhilttest.utils.ImageLoader
import com.exercise.mvvmhilttest.databinding.ItemRepositoryPreviewBinding

class RepositoriesAdapter(
    private val imageLoader: ImageLoader,
    private val listener: Listener? = null
) : RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder>() {

    private val repositories = arrayListOf<Repository>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val itemBinding = ItemRepositoryPreviewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoriesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) =
        holder.bindView(repositories[position])

    override fun onViewRecycled(holder: RepositoriesViewHolder) {
        super.onViewRecycled(holder)
        with(holder.binding) { imageLoader.clear(root.context, vAvatar) }
    }

    override fun getItemCount(): Int = repositories.size

    fun isEmpty() = repositories.isEmpty()

    fun insertToEnd(repositories: List<Repository>) {
        val positionStart = if (repositories.isEmpty()) 0 else this.repositories.size
        this.repositories.addAll(repositories)
        notifyItemRangeInserted(positionStart, repositories.size)
    }

    inner class RepositoriesViewHolder(val binding: ItemRepositoryPreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(data: Repository): Unit = with(binding) {
            vName.text = data.fullName
            vCreationDate.text = data.createdOn
            updateAvatar(data)
            updateType(data)
            root.setOnClickListener { listener?.onItemPressed(data) }
        }

        private fun updateAvatar(data: Repository) = with(binding) {
            imageLoader.load(root.context, data.ownerAvatar ?: "", binding.vAvatar)
        }

        private fun updateType(data: Repository) = with(binding.vType) {
            data.type?.let {
                text = it
                visibility = VISIBLE
            } ?: run { visibility = GONE }
        }
    }

    interface Listener {
        fun onItemPressed(repository: Repository)
    }
}