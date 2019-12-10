package ru.shepetov.navicotest.ui.attractionsList

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
import kotlinx.android.synthetic.main.fragment_attraction_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.shepetov.navicotest.BaseFragment
import ru.shepetov.navicotest.R
import ru.shepetov.navicotest.databinding.FragmentAttractionListBinding
import ru.shepetov.navicotest.observeOnes
import ru.shepetov.navicotest.ui.attractionsList.AttractionListFragmentDirections.Companion.actionAttracionListToAttractionDetails

class AttractionListFragment : BaseFragment() {
    val viewModel: AttractionListViewModel by viewModel()
    lateinit var attractionsAdapter: AttractionListAdapter
    lateinit var binding: FragmentAttractionListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_attraction_list,
            container, false
        ) ?: return null

        return binding.also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.attractionListStateInterceptorScreen.stateInterceptor = viewModel

        attractionsAdapter = AttractionListAdapter(viewModel.clickEvent)

        attractionList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = attractionsAdapter
            itemAnimator = null
            setHasFixedSize(true)
        }

        initObservers()

        initSearchView()
    }

    private fun initObservers() = with(viewModel) {

        attractionsList.observe(this@AttractionListFragment, Observer { attractions ->
            attractionsAdapter.updateItems(attractions)
        })

        clickEvent.observeOnes(this@AttractionListFragment, Observer { attraction ->
            navigator?.navigate(actionAttracionListToAttractionDetails(attraction))
        })

        query.observe(this@AttractionListFragment, Observer {
            viewModel.search(it)
        })
    }

    private fun initSearchView() {

        binding.attractionListSearchView.apply {
            onActionViewExpanded()
            clearFocus()

            findViewById<EditText?>(R.id.search_src_text)?.apply {
                filters = arrayOf(InputFilter.LengthFilter(SEARCH_VIEW_MAX_LENGTH))
            }

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = false

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.query.value = newText ?: return false
                    return true
                }
            })
        }

        binding.attractionList.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState != SCROLL_STATE_DRAGGING) return
                binding.attractionListSearchView.clearFocus()
            }
        })
    }

    override fun onStop() {
        binding.attractionListSearchView.clearFocus()
        super.onStop()
    }

    companion object {
        private const val SEARCH_VIEW_MAX_LENGTH = 60
    }
}
