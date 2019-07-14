package com.cabify.store.product.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import com.cabify.store.core.android.presentation.adapter.BasicAdapter
import com.cabify.store.core.android.presentation.base.BaseFragment
import com.cabify.store.core.android.presentation.extensions.observe
import com.cabify.store.core.android.presentation.extensions.obtainViewModel
import com.cabify.store.core.android.presentation.extensions.visibleOrGone
import com.cabify.store.core.android.presentation.extensions.visibleOrInvisible
import com.cabify.store.core.android.presentation.viewdata.ViewDataObserver
import com.cabify.store.product.R
import com.cabify.store.product.presentation.data.HomeItemViewData
import com.cabify.store.product.presentation.data.HomeViewData
import com.cabify.store.product.presentation.view.detail.ProductDetailFragment
import com.cabify.store.product.presentation.viewmodel.HomeViewModel
import com.cabify.store.product.presentation.viewmodel.HomeViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment(), ViewDataObserver<HomeViewData> {

    @Inject
    lateinit var vmFactory: HomeViewModelFactory

    lateinit var viewModel: HomeViewModel

    override val layoutRes: Int
        get() = R.layout.fragment_home

    private lateinit var adapter: BasicAdapter<HomeItemViewData>

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = obtainViewModel(vmFactory)
        adapter = BasicAdapter(
            HomeProductViewHolderManager(::onProductClicked),
            HomeTitleViewHolderManager()
        )

        homeRecycler.adapter = adapter
        viewModel.viewData.observe(this, this)
        viewModel.start()

        errorView.setOnClickListener { viewModel.start() }
    }

    override fun onLoading(isLoading: Boolean) {
        loader.visibleOrGone(isLoading)
        homeRecycler.visibleOrInvisible(!isLoading)
        errorView.visibleOrGone(false)
    }

    override fun onNewData(viewData: HomeViewData) {
        adapter.data = viewData.recyclerData
        adapter.notifyDataSetChanged()
    }

    override fun onDataError(error: Throwable) {
        errorView.visibleOrGone(true)
        homeRecycler.visibleOrInvisible(false)
    }

    private fun onProductClicked(productId: String) {
        ProductDetailFragment.newInstance(productId)
            .show(childFragmentManager, "ProductDetailFragment")
    }

}