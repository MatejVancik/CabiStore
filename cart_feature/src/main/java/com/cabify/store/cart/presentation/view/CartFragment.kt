package com.cabify.store.cart.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import com.cabify.store.cart.R
import com.cabify.store.cart.presentation.data.CartItemViewData
import com.cabify.store.cart.presentation.data.CartViewData
import com.cabify.store.cart.presentation.view.detail.CartItemDetailFragment
import com.cabify.store.cart.presentation.viewmodel.CartViewModel
import com.cabify.store.cart.presentation.viewmodel.CartViewModelFactory
import com.cabify.store.core.android.presentation.adapter.BasicAdapter
import com.cabify.store.core.android.presentation.base.BaseFragment
import com.cabify.store.core.android.presentation.extensions.observe
import com.cabify.store.core.android.presentation.extensions.obtainViewModel
import com.cabify.store.core.android.presentation.viewdata.ViewDataObserver
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_cart.*
import javax.inject.Inject

class CartFragment : BaseFragment(), ViewDataObserver<CartViewData> {

    @Inject
    lateinit var vmFactory: CartViewModelFactory

    lateinit var viewModel: CartViewModel

    lateinit var adapter: BasicAdapter<CartItemViewData>

    override val layoutRes: Int
        get() = R.layout.fragment_cart

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BasicAdapter(
            CartItemViewHolderManager(::onCartItemClicked)
        )

        itemsRecyclerView.adapter = adapter

        viewModel = obtainViewModel(vmFactory)
        viewModel.viewData.observe(this, this)
        viewModel.start()
    }

    private fun onCartItemClicked(cartItemViewData: CartItemViewData) {
        CartItemDetailFragment.newInstance(cartItemViewData.code)
            .show(childFragmentManager, CartItemDetailFragment::class.java.name)
    }

    override fun onLoading(isLoading: Boolean) {

    }

    override fun onNewData(viewData: CartViewData) {
        totalPriceTextView.text = viewData.totalPrice
        discountTextView.text = viewData.discount
        toPayTextView.text = viewData.finalPrice

        adapter.data = viewData.items
        adapter.notifyDataSetChanged()
    }

    override fun onDataError(error: Throwable) {

    }

}