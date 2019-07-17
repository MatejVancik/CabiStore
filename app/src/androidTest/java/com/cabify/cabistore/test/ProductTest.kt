package com.cabify.cabistore.test

import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import com.cabify.cabistore.R
import com.cabify.cabistore.extensions.*
import org.hamcrest.Matchers.*
import org.junit.Test

class ProductTest : BaseTest() {

    @Test
    fun allItemsVisibleOnHomePage() {
        onView(
            allOf(
                instanceOf(AppCompatTextView::class.java),
                withParent(withId(R.id.toolbar))
            )
        ).checkText("Home")

        scrollRecyclerTo("Our products")
        assertListItem("Cabify Voucher", "Cabify Voucher", "5.00€", true)
        assertListItem("Cabify T-Shirt", "Cabify T-Shirt", "20.00€", true)
        assertListItem("Cabify Coffee Mug", "Cabify Coffee Mug", "7.50€", false)
    }

    @Test
    fun productDetailDisplayedCorrectly() {
        val contentDescription = "Cabify T-Shirt"
        tapOnItem(contentDescription)

        assertItemDetail(
            "Cabify T-Shirt",
            "20.00€",
            "Special offer",
            "Buy at least 3 pieces of Cabify T-Shirt and you'll save 5% on each!"
        )
    }

    companion object {

        fun scrollRecyclerTo(contentDescription: String) {
            onView(withId(R.id.homeRecycler))
                .perform(scrollTo<RecyclerView.ViewHolder>(withContentDescription(contentDescription)))
        }

        fun tapOnItem(contentDescription: String) {
            scrollRecyclerTo(contentDescription)
            onView(withContentDescription(contentDescription)).tap()
        }

        private fun assertListItem(contentDescription: String, name: String, price: String, hasOffer: Boolean) {
            scrollRecyclerTo(contentDescription)
            fun view(id: Int) = onView(allOf(withId(id), withParent(withContentDescription(contentDescription))))

            view(R.id.title).checkText(name)
            view(R.id.price).checkText(price)

            view(R.id.offerLabel).run { if (hasOffer) isVisible() else isInvisible() }
        }

        private fun assertItemDetail(name: String, price: String, offer: String? = null, body: String? = null) {
            onView(withId(R.id.nameTextView)).checkText(name)
            onView(withId(R.id.priceTextView)).checkText(price)

            fun String?.assertInViewWithId(id: Int) = onView(withId(id)).let {
                if (this != null) it.checkText(this) else it.isInvisible()
            }

            offer?.assertInViewWithId(R.id.offerTitle)
            body?.assertInViewWithId(R.id.offerBody)
        }
    }

}