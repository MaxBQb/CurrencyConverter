package lab.maxb.currency_converter.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import lab.maxb.currency_converter.databinding.MainFragmentBinding
import lab.maxb.currency_converter.presentation.repository.implementations.network.exchangerateAPI.NetworkStateListener
import lab.maxb.currency_converter.presentation.viewModel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel()
    private val networkStateListener: NetworkStateListener by inject()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return MainFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@MainFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        networkStateListener.connectionState.observe(
            viewLifecycleOwner,
            viewModel::handleNetworkState
        )
    }
}