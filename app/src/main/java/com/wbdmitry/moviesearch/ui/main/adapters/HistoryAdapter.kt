package com.wbdmitry.moviesearch.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wbdmitry.moviesearch.databinding.FragmentItemHistoryBinding
import com.wbdmitry.moviesearch.model.entity.History

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private lateinit var binding: FragmentItemHistoryBinding
    private var data: List<History> = listOf()

    fun setData(data: List<History>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        binding = FragmentItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HistoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class HistoryViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        fun bind(data: History) = with(binding) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                timeTextView.text = data.time
                movieTitleTextView.text = data.movie_title
            }
        }
    }
}