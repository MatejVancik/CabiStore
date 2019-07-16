package com.cabify.store.product.presentation.data.mapper.detail

import com.cabify.store.core.android.utils.ResourceProvider
import com.cabify.store.product.voucherProductData
import com.cabify.store.product.voucherProductDetailViewData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductDetailViewDataMapperTest {

    @Mock
    lateinit var resourceProvider: ResourceProvider

    private lateinit var mapper: ProductDetailViewDataMapper

    @Before
    fun setup() {
        mapper = ProductDetailViewDataMapper(resourceProvider)
    }

    @Test
    fun `product to detail ViewData`() {
        whenever(resourceProvider.getString(any(), any())).thenReturn("Promo text")
        val actualViewData = mapper.productToDetailViewData(voucherProductData)

        Assert.assertEquals(voucherProductDetailViewData, actualViewData)
    }

}