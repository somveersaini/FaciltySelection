package com.sam.faciltyselection.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sam.faciltyselection.R
import com.sam.faciltyselection.databinding.FacilitySelectionFragmentBinding
import com.sam.faciltyselection.repository.model.FacilitySelectionListItem
import com.sam.faciltyselection.ui.adapter.FacilitySelectionAdapter
import com.sam.faciltyselection.ui.listner.ItemClickHandler
import com.sam.faciltyselection.viewmodel.FacilitySelectionViewModel
import com.sam.faciltyselection.viewmodel.FacilitySelectionViewState
import com.sam.faciltyselection.viewmodel.SuccessState
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FacilitySelectionFragment : Fragment(), ItemClickHandler {

    companion object {
        fun newInstance() = FacilitySelectionFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: FacilitySelectionAdapter

    private lateinit var viewModel: FacilitySelectionViewModel
    private lateinit var binding: FacilitySelectionFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding  = DataBindingUtil.inflate(inflater, R.layout.facility_selection_fragment, container, false)
        return binding.root
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FacilitySelectionViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecylerView()
        observeForcastViewModel()
    }

    private fun initRecylerView() {
        adapter.clickHandler = this
        with(binding.content.listRecyclerView) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
            adapter = this@FacilitySelectionFragment.adapter
        }
    }

    private fun observeForcastViewModel() {
        viewModel.getFacilitySelectionViewState().observe(this, Observer<FacilitySelectionViewState> { state -> render(state) })
        viewModel.fetchFacilitySelectionData()
    }

    private fun render(viewState: FacilitySelectionViewState) {
        binding.viewState = viewState
        if (viewState is SuccessState) renderList(viewState)
    }

    var listChange = listOf<Int>()
    private fun renderList(viewState: SuccessState) {
        Log.d("ListData", viewState.facilitySelectionModel.toString())
        adapter.update(viewState.facilitySelectionModel.facilityOption)
        if (listChange.isEmpty()) {
            adapter.notifyDataSetChanged()
        }
        else {
            listChange.forEach{ item -> adapter.notifyItemChanged(item)}
        }
    }

    override fun onClick(position: Int): Boolean {
        listChange = viewModel.select(position)
        if (listChange.isEmpty()){
            Toast.makeText(activity, "Not Supported config", Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

}