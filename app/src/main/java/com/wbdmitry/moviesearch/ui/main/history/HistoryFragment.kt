package com.wbdmitry.moviesearch.ui.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wbdmitry.moviesearch.databinding.HistoryFragmentBinding
import com.wbdmitry.moviesearch.model.repository.RepositoryImpl
import com.wbdmitry.moviesearch.model.repository.retrofit.RemoteDataSource
import com.wbdmitry.moviesearch.ui.main.adapters.HistoryAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HistoryFragment : Fragment(), CoroutineScope by MainScope() {
    private lateinit var binding: HistoryFragmentBinding
    private val viewModel: HistoryViewModel by viewModel {
        parametersOf(RepositoryImpl(RemoteDataSource()))
    }
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    companion object {
        fun newInstance() = HistoryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HistoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.historyRecyclerView.adapter = adapter
        viewModel.historyLiveData.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })
        launch(Dispatchers.IO) {
            try {
                viewModel.getAllHistory()
            } catch (exception: Exception) {
                Toast.makeText(
                    context,
                    "Error: " + exception.localizedMessage,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }
}