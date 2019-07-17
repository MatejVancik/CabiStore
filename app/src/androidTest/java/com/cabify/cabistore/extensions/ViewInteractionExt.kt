package com.cabify.cabistore.extensions

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matchers.not

fun ViewInteraction.checkText(text: String) {
    check(matches(ViewMatchers.withText(text)))
}

fun ViewInteraction.isVisible() {
    check(matches(isDisplayed()))
}

fun ViewInteraction.isInvisible() {
    check(matches(not(isDisplayed())))
}

fun ViewInteraction.tap() {
    perform(click())
}

fun Int.asView(): ViewInteraction {
    return onView(withId(this))
}