package com.cabify.store.product.presentation.data.mapper

import com.cabify.store.core.android.utils.ResourceProvider
import com.cabify.store.product.*
import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.presentation.data.HomeProductItemViewData
import com.cabify.store.product.presentation.data.HomeTitleItemViewData
import com.cabify.store.product.presentation.data.HomeViewData
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewDataMapperTest {

    @Mock
    lateinit var resourceProvider: ResourceProvider

    private lateinit var mapper: HomeViewDataMapper

    @Before
    fun setup() {
        mapper = HomeViewDataMapper(resourceProvider)
    }

    @Test
    fun `map products data to ViewData`() {
        val title = "Section title"
        whenever(resourceProvider.getString(R.string.our_products)).thenReturn(title)
        val productData = listOf(mugProductData, voucherProductData)
        val expectedViewData = HomeViewData(
            listOf(
                HomeTitleItemViewData(title.hashCode().toLong(), title),
                mugHomeProductItemViewData,
                voucherHomeProductItemViewData
            )
        )

        val actualResult = mapper.mapProductDataToViewData(productData)
        Assert.assertEquals(expectedViewData, actualResult)
    }

}