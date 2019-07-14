package com.cabify.store.product.presentation.view.detail

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import com.bumptech.glide.Glide
import com.cabify.store.core.android.presentation.extensions.*
import com.cabify.store.core.android.presentation.viewdata.ViewDataObserver
import com.cabify.store.product.R
import com.cabify.store.product.presentation.data.ProductDetailViewData
import com.cabify.store.product.presentation.viewmodel.detail.DetailViewModel
import com.cabify.store.product.presentation.viewmodel.detail.DetailViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_product_detail.*
import javax.inject.Inject

class ProductDetailFragment : BottomSheetDialogFragment(), ViewDataObserver<ProductDetailViewData> {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                val bottomSheetInternal = findViewById<ViewGroup>(R.id.design_bottom_sheet).apply {
                    layoutParams.height = MATCH_PARENT
                }

                BottomSheetBehavior.from(bottomSheetInternal).apply {
                    skipCollapsed = true
                    peekHeight = Resources.getSystem().displayMetrics.heightPixels
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments!!.getString(KEY_PRODUCT_ID)!!

        viewModel = obtainViewModel(vmFactory)

        countPicker.countChangeListener = { addToCartButton.isEnabled = it > 0 }
        addToCartButton.setOnClickListener {
            viewModel.addToBasket(countPicker.count)
            toast(R.string.message_product_added)
        }

        viewModel.viewData.observe(this, this)
        viewModel.start(productId)
    }

    override fun onLoading(isLoading: Boolean) {
        loader.visibleOrGone(isLoading)
        contentViews.visibleOrInvisible(!isLoading)
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
        // TODO: Proper error handling to be added as a future improvement.
        dismiss()
    }

}