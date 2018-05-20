package pl.edu.amu.wmi.erykandroidcommon.service

import android.content.Context
import android.widget.ImageView

import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

import timber.log.Timber
import java.lang.Exception

public class PicassoCache(private val context: Context) {

    private val picasso: Picasso

    init {
        val builder = Picasso.Builder(context)
        builder.downloader(OkHttp3Downloader(context, Integer.MAX_VALUE.toLong()))
        picasso = builder.build()
        Picasso.setSingletonInstance(picasso)
    }

    fun getImage(imageView: ImageView, url: String) {
        picasso
            .load(url)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(imageView, object : Callback {
                override fun onError(e: Exception?) {
                    e?.let { Timber.d(e) }
                    picasso
                        .load(url)
                        .into(imageView)
                }

                override fun onSuccess() {
                    Timber.i("Image fetched")
                }
            })
    }
}

