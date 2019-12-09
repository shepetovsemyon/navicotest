package ru.shepetov.navicotest.ui.attractionsList


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_attracion_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.shepetov.navicotest.R
import ru.shepetov.navicotest.ui.attractionsList.AttractionListFragmentDirections.Companion.actionAttracionListToAttractionDetails

class AttractionListFragment : BaseFragment() {
    val viewModel: AttractionsViewModel by viewModel()
    lateinit var attractionsAdapter: AttractionListAdapter

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

        attractionsAdapter = AttractionListAdapter(viewModel.clickEvent)

        attractionList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = attractionsAdapter
            itemAnimator = null
            setHasFixedSize(true)
        }

        viewModel.attractionsList.observe(this, Observer {attractions ->
            attractionsAdapter.updateItems(attractions)
        })

        viewModel.clickEvent.observe(this, Observer {
            val attraction = it ?: return@Observer

            navigator?.navigate(actionAttracionListToAttractionDetails(attraction))
            viewModel.clickEvent.value = null
        })
    }
}
