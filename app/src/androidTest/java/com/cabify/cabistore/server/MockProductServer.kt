package com.cabify.cabistore.server

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

private const val mockProductResponse = "{\"products\":[" +
    "{\"code\":\"VOUCHER\",\"name\":\"Cabify Voucher\",\"price\":5}," +
    "{\"code\":\"TSHIRT\",\"name\":\"Cabify T-Shirt\",\"price\":20}," +
    "{\"code\":\"MUG\",\"name\":\"Cabify Coffee Mug\",\"price\":7.5}]}"

class MockProductServer {

    companion object {
        val baseUrl = "http://localhost:8080/"
    }

    var server = MockWebServer()

    fun start() {
        server = MockWebServer().apply {
            start(8080)
            setDispatcher(this@MockProductServer.dispatcher)
        }
    }

    fun stop() {
        server.shutdown()
    }

    val dispatcher = object : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().apply {
                when (request.path) {
                    "/bins/4bwec" -> {
                        setResponseCode(200)
                        setBody(mockProductResponse)
                    }
                    else -> throw Error("Request with path ${request.path} is not mocked yet!")
                }
            }
        }
    }

}

