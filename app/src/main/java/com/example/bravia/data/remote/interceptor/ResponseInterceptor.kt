package com.example.bravia.data.remote.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import javax.inject.Inject

class ResponseInterceptor @Inject constructor() : Interceptor {

    /**
     * Intercepts the HTTP response to log and optionally modify it.
     *
     * @param chain The interceptor chain.
     * @return The intercepted and potentially modified response.
     * @throws IOException If an I/O error occurs during the interception.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        // Proceed with the request
        val response = chain.proceed(chain.request())

        // Convert the response body to a string
        val responseBodyString = response.body?.string()

        // Log the raw response
        Log.d("ResponseInterceptor", "Raw Response: $responseBodyString")
        //println("Raw Response: $responseBodyString") TODO: Creo que el profe lo va a quitar

        // Return the response by re-creating the body with the intercepted content
        return response.newBuilder()
            .body((responseBodyString ?: "").toResponseBody(response.body?.contentType()))
            .build()
    }
}