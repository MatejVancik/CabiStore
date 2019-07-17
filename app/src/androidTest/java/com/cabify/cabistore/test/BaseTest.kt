package com.cabify.cabistore.test

import androidx.test.rule.ActivityTestRule
import com.cabify.cabistore.presentation.view.MainActivity
import com.cabify.cabistore.server.MockProductServer
import org.junit.After
import org.junit.Before
import org.junit.Rule

open class BaseTest {

    @get:Rule
    val main = ActivityTestRule(MainActivity::class.java, true, false)

    private lateinit var server: MockProductServer

    @Before
    open fun setup() {
        server = MockProductServer().apply { start() }
        main.launchActivity(null)
    }

    @After
    open fun tearDown() {
        server.stop()
    }

}