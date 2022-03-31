package com.example.movies.handle.helper

import android.content.Context
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class Requests {
    companion object {
        fun sendGet(x: String?, cnt: Context?): String? {

            return if (x != null && x.contains("https")) {
                val builder = StringBuilder()
                var urlConnection: HttpsURLConnection? = null
                var `in`: InputStream? = null
                try {
                    //            SSLContext context=getsslcontexterror1(cnt);
                    val url = URL(x)
                    urlConnection = url.openConnection() as HttpsURLConnection
                    //            urlConnection.setSSLSocketFactory(context.getSocketFactory());
                    //                urlConnection.setHostnameVerifier(BaseActivity.hostnameVerifier);
                    urlConnection.instanceFollowRedirects = false
                    urlConnection!!.requestMethod = "GET"
                    urlConnection.setRequestProperty("Content-Type", "text/plain")
                    urlConnection.setRequestProperty("charset", "utf-8")

                    urlConnection.setRequestProperty("Connection", "Keep-Alive")
                    urlConnection.connect()
                    `in` = urlConnection.inputStream
                    val reader = BufferedReader(
                        InputStreamReader(`in`)
                    )
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        builder.append(line)
                    }
                } catch (e1: MalformedURLException) {
                    e1.printStackTrace()
                } catch (e1: IOException) {
                    e1.printStackTrace()
                }
                if (urlConnection != null && `in` != null) {
                    urlConnection.disconnect()
                    try {
                        `in`.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }



                builder.toString()
            } else {
                val builder = StringBuilder()
                var urlConnection: HttpURLConnection? = null
                var `in`: InputStream? = null
                try {
                    val url = URL(x)
                    urlConnection = url.openConnection() as HttpURLConnection
                    urlConnection!!.instanceFollowRedirects = false
                    urlConnection.requestMethod = "GET"

                    urlConnection.setRequestProperty("Connection", "Keep-Alive")
                    urlConnection.setRequestProperty("Content-Type", "text/plain")
                    urlConnection.setRequestProperty("charset", "utf-8")
                    urlConnection.connect()
                    `in` = urlConnection.inputStream
                    val reader = BufferedReader(
                        InputStreamReader(`in`)
                    )
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        builder.append(line)
                    }
                } catch (e1: MalformedURLException) {
                    e1.printStackTrace()
                } catch (e1: IOException) {
                    e1.printStackTrace()
                }
                if (urlConnection != null && `in` != null) {
                    urlConnection.disconnect()
                    try {
                        `in`.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                var rez: JSONObject? = null

                builder.toString()
            }
        }
    }
}