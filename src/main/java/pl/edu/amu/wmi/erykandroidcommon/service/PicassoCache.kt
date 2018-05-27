package pl.edu.amu.wmi.erykandroidcommon.service

import android.content.Context
import android.widget.ImageView

import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

import timber.log.Timber
import java.lang.Exception

class PicassoCache(context: Context) {

    private val picasso: Picasso = Picasso
        .Builder(context)
        .downloader(OkHttp3Downloader(context, Integer.MAX_VALUE.toLong()))
        .build()

    init {
        Picasso.setSingletonInstance(picasso)
    }

    fun getImage(imageView: ImageView, url: String) {
        picasso
            .load(url)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(imageView, object : Callback {
                override fun onError(e: Exception?) {
                    e?.let { Timber.v(e) }
                    picasso
                        .load(url)
                        .into(imageView)
                }

                override fun onSuccess() {
                    Timber.v("Image fetched")
                }
            })
    }
}

