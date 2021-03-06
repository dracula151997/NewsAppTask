package com.task.newsapptask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.task.newsapptask.R
import com.task.newsapptask.databinding.ArticlesLoadStateFooterViewItemBinding

class ArticlesLoadStateViewHolder(
    private val binding: ArticlesLoadStateFooterViewItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): ArticlesLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.articles_load_state_footer_view_item, parent, false)
            val binding = ArticlesLoadStateFooterViewItemBinding.bind(view)
            return ArticlesLoadStateViewHolder(binding, retry)
        }
    }

}