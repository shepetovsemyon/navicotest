package ru.shepetov.navicotest.ui.attractionsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import ru.shepetov.navicotest.R
import ru.shepetov.navicotest.data.Attraction
import ru.shepetov.navicotest.databinding.ItemAttractionBinding

class AttractionListAdapter(private val clickEvent: MutableLiveData<Attraction?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<Attraction>()

    fun updateItems(attractions: List<Attraction>) {
        items.clear()
        items.addAll(attractions)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemAttractionBinding>(
                inflater, R.layout.item_attraction,
                parent, false
            )

        return AttractionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as AttractionViewHolder).bind(items[position])

    private inner class AttractionViewHolder(
        private val binding: ItemAttractionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Attraction) {
            binding.attraction = item
            binding.root.setOnClickListener {
                clickEvent.value = item
            }
        }
    }

    override fun getItemCount(): Int = items.count()
}