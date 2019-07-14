package com.cabify.store.cart.presentation.view.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.cabify.store.cart.R
import com.cabify.store.cart.presentation.data.*
import com.cabify.store.cart.presentation.viewmodel.detail.CartItemDetailViewModel
import com.cabify.store.cart.presentation.viewmodel.detail.CartItemDetailViewModelFactory
import com.cabify.store.core.android.presentation.event.Event
import com.cabify.store.core.android.presentation.extensions.*
import com.cabify.store.core.android.presentation.viewdata.ViewDataObserver
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_cart_item_detail.*
import javax.inject.Inject

class CartItemDetailFragment : BottomSheetDialogFragment(), ViewDataObserver<CartItemDetailViewData> {

    companion object {
        private const val KEY_PRODUCT_ID = "productId"

        @JvmStatic
        fun newInstance(productId: String) = CartItemDetailFragment().apply {
            arguments = Bundle().apply { putString(KEY_PRODUCT_ID, productId) }
        }
    }

    @Inject
    lateinit var vmFactory: CartItemDetailViewModelFactory

    lateinit var viewModel: CartItemDetailViewModel

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments!!.getString(KEY_PRODUCT_ID)!!
        viewModel = obtainViewModel(vmFactory)

        viewModel.viewData.observe(this, this)
        viewModel.events.observe(this, onEventObserver())
        viewModel.start(productId)

        deleteButton.setOnClickListener { viewModel.deleteCartItem() }
    }

    override fun onLoading(isLoading: Boolean) {
        loader.visibleOrGone(isLoading)
        contentViews.visibleOrInvisible(!isLoading)
    }

    override fun onNewData(viewData: CartItemDetailViewData) {
        nameTextView.text = viewData.name
        Glide.with(this).load(viewData.image).into(imageView)

        countPicker.countChangeListener = null
        countPicker.count = viewData.count
        countPicker.countChangeListener = { viewModel.setNumberOfItems(it) }

        pricePerItemValueTextView.text = viewData.pricePerItem
        discountValueTextView.text = viewData.discount
        totalPriceValueTextView.text = viewData.totalPrice
    }

    override fun onDataError(error: Throwable) {
        toast(R.string.error_loading_cart_item)
        dismiss()
    }

    private fun onEventObserver() = Observer<Event<CartItemDetailEvent>> {
        when (it.getContentIfNotHandled()) {
            DismissDetail -> {
                toast(R.string.message_cart_item_deleted)
                dismiss()
            }
            DeleteFailed -> { toast(R.string.error_delete_cart_item) }
            UpdateFailed -> { toast(R.string.error_update_cart_item) }
        }
    }

}