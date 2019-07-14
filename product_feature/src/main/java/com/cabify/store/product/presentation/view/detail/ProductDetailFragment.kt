package com.cabify.store.product.presentation.view.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.cabify.store.core.android.presentation.extensions.observe
import com.cabify.store.core.android.presentation.extensions.obtainViewModel
import com.cabify.store.core.android.presentation.viewdata.ViewDataObserver
import com.cabify.store.product.R
import com.cabify.store.product.presentation.data.ProductDetailViewData
import com.cabify.store.product.presentation.viewmodel.detail.DetailViewModel
import com.cabify.store.product.presentation.viewmodel.detail.DetailViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_product_detail.*
import javax.inject.Inject

class ProductDetailFragment : DialogFragment(), ViewDataObserver<ProductDetailViewData> {

    companion object {
        private const val KEY_PRODUCT_ID = "productId"

        @JvmStatic
        fun newInstance(productId: String) = ProductDetailFragment().apply {
            arguments = Bundle().apply { putString(KEY_PRODUCT_ID, productId) }
        }
    }

    @Inject
    lateinit var vmFactory: DetailViewModelFactory

    lateinit var viewModel: DetailViewModel

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_detail, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments!!.getString(KEY_PRODUCT_ID)

        viewModel = obtainViewModel(vmFactory)

        countPicker.countChangeListener = { addToCartButton.isEnabled = it > 0 }
        addToCartButton.setOnClickListener { viewModel.addToBasket(countPicker.count) }

        viewModel.viewData.observe(this, this)
        viewModel.start(productId)
    }

    override fun onLoading(isLoading: Boolean) {

    }

    override fun onNewData(viewData: ProductDetailViewData) {
        nameTextView.text = viewData.title
        priceTextView.text = viewData.price

        viewData.promotion?.let {
            offerGroup.visibility = View.VISIBLE
            offerBody.text = it
        }

        Glide.with(this).load(viewData.image).into(imageView)
    }

    override fun onDataError(error: Throwable) {

    }

}