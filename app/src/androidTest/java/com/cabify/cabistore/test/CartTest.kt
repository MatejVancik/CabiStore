package com.cabify.cabistore.test

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.cabify.cabistore.CabiStoreTestApp
import com.cabify.cabistore.R
import com.cabify.cabistore.di.UserSessionTestComponent
import com.cabify.cabistore.extensions.*
import com.cabify.store.cart.repo.CartRepository
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class CartTest : BaseTest() {

    @Inject
    lateinit var cartRepository: CartRepository

    override fun setup() {
        (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as CabiStoreTestApp).apply {
            startUserSession()
            (userSessionComponent as UserSessionTestComponent).inject(this@CartTest)
        }
        super.setup()
    }

    @Test
    fun emptyCartViewDisplayed() {
        R.id.nav_cart.asView().tap()
        R.id.emptyCartView.asView().checkText("\uD83D\uDE22\nYour shopping cart is empty.\nGo and add something!")

        listOf(
            R.id.loader,
            R.id.errorView,
            R.id.itemsRecyclerView,
            R.id.totalPriceLabel,
            R.id.totalPriceTextView,
            R.id.discountLabel,
            R.id.discountTextView,
            R.id.divider,
            R.id.toPayLabel,
            R.id.toPayTextView,
            R.id.payButton
        ).forEach { it.asView().isInvisible() }
    }

    @Test
    fun addItemToCart() {
        cartRepository.addToCart("VOUCHER", 2).blockingGet()

        R.id.nav_cart.asView().tap()

        scrollRecyclerTo("VOUCHER")
        assertItem("VOUCHER", "2x Cabify Voucher", "5.00€", "10.00€")
        R.id.totalPriceTextView.asView().checkText("10.00€")
        R.id.discountTextView.asView().checkText("5.00€")
        R.id.toPayTextView.asView().checkText("5.00€")
    }

    @Test
    fun calculateDiscount() {
        cartRepository.addToCart("VOUCHER", 2).blockingGet()
        R.id.nav_cart.asView().tap()

        scrollRecyclerTo("VOUCHER")
        onView(withContentDescription("VOUCHER")).tap()

        R.id.increaseButton.asView().tap()
        assertDetailContent(3, "5.00€", "5.00€", "10.00€")
    }

    @Test
    fun deleteCartItem() {
        cartRepository.addToCart("VOUCHER", 1).blockingGet()

        R.id.nav_cart.asView().tap()

        scrollRecyclerTo("VOUCHER")
        onView(withContentDescription("VOUCHER")).tap()

        R.id.deleteButton.asView().tap()

        R.id.itemsRecyclerView.asView().isInvisible()
        R.id.emptyCartView.asView().isVisible()
    }

    companion object {

        fun scrollRecyclerTo(contentDescription: String) {
            R.id.itemsRecyclerView.asView()
                .perform(scrollTo<RecyclerView.ViewHolder>(withContentDescription(contentDescription)))
        }

        fun assertDetailContent(items: Int, perItem: String, discount: String, total: String) {
            R.id.countText.asView().checkText(items.toString())
            R.id.pricePerItemValueTextView.asView().checkText(perItem)
            R.id.discountValueTextView.asView().checkText(discount)
            R.id.totalPriceValueTextView.asView().checkText(total)
        }

        private fun assertItem(
            contentDescription: String,
            title: String,
            price: String,
            originalPrice: String? = null
        ) {
            fun view(id: Int) = onView(allOf(withId(id), withParent(withContentDescription(contentDescription))))

            view(R.id.title).checkText(title)
            view(R.id.price).checkText(price)
            view(R.id.originalPrice).let {
                if (originalPrice != null) it.checkText(originalPrice) else it.isInvisible()
            }
        }
    }

}