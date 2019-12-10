package ru.shepetov.navicotest.ui.attractionDetails

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

import ru.shepetov.navicotest.R
import ru.shepetov.navicotest.data.Location
import ru.shepetov.navicotest.databinding.FragmentAttractionDetailsBinding
import ru.shepetov.navicotest.BaseFragment

class AttractionDetailsFragment : BaseFragment() {
    private val args by navArgs<AttractionDetailsFragmentArgs>()

    val viewModel: AttractionDetailsViewModel by viewModel()
    val packageManager: PackageManager by inject()

    lateinit var binding: FragmentAttractionDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_attraction_details,
            container, false
        ) ?: return null

        viewModel.attraction.value = args.attraction

        return binding.also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.attractionDetailsShowInMapBtn.setOnClickListener {
            viewModel.attraction.value?.location?.let { location ->
                showMap(location)
            }
        }

        val activity = activity as? AppCompatActivity ?: return
        binding.appBar.apply {
            navigationIcon = ContextCompat.getDrawable(activity, R.drawable.ic_arrow_back)

            setNavigationOnClickListener {
                navigator?.popBackStack()
            }
        }
    }

    private fun showMap(location: Location) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("geo:${location.lat},${location.lng}")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
