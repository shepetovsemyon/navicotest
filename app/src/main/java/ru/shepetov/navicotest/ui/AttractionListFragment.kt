package ru.shepetov.navicotest.ui


import android.graphics.Region
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_attracion_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.shepetov.navicotest.R
import ru.shepetov.navicotest.data.Attraction
import ru.shepetov.navicotest.databinding.ItemAttractionBinding

/**
 * A simple [Fragment] subclass.
 */
class AttractionListFragment : Fragment() {
    val viewModel: AttractionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attracion_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val attractionsAdapter = AttractionListAdapter()

        attractionList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = attractionsAdapter
            itemAnimator = null
            setHasFixedSize(true)
        }

        viewModel.attractionsList.observe(this, Observer {attractions ->
            attractionsAdapter.updateItems(attractions)
        })
    }
}


class AttractionListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<Attraction>()

    fun updateItems(attractions: List<Attraction>) {
        items.clear()
        items.addAll(attractions)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemAttractionBinding>(
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
//            binding.root.setOnClickWithDoubleCheck {
//                regionListViewModel.onItemClick(item)
//            }
        }
    }

    override fun getItemCount(): Int = items.count()
}

