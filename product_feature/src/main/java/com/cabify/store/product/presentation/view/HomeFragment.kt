package com.cabify.store.product.presentation.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider.Factory
import com.cabify.store.core.android.presentation.adapter.BasicAdapter
import com.cabify.store.core.android.presentation.base.BaseFragment
import com.cabify.store.core.android.presentation.extensions.observe
import com.cabify.store.core.android.presentation.extensions.obtainViewModel
import com.cabify.store.core.android.presentation.viewdata.ViewDataObserver
import com.cabify.store.product.R
import com.cabify.store.product.presentation.data.HomeItemViewData
import com.cabify.store.product.presentation.data.HomeViewData
import com.cabify.store.product.presentation.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment(), ViewDataObserver<HomeViewData> {

    @Inject
    lateinit var vmFactory: Factory

    lateinit var viewModel: HomeViewModel

    override val layoutRes: Int
        get() = R.layout.fragment_home

    private lateinit var adapter: BasicAdapter<HomeItemViewData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = obtainViewModel(vmFactory, HomeViewModel::class.java)
        adapter = BasicAdapter(
            HomeProductViewHolderManager(::onProductClicked),
            HomeTitleViewHolderManager()
        )

        homeRecycler.adapter = adapter
        viewModel.viewData.observe(this, this)
    }

    override fun onLoading(isLoading: Boolean) {

    }

    override fun onNewData(viewData: HomeViewData) {
        adapter.data = viewData.recyclerData
        adapter.notifyDataSetChanged()
    }

    override fun onDataError(error: Throwable) {

    }

    private fun onProductClicked(productId: String) {

    }

}