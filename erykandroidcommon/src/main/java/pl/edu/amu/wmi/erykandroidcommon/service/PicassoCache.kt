package pl.edu.amu.wmi.erykandroidcommon.service

import android.content.Context
import android.widget.ImageView

import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.OkHttpDownloader
import com.squareup.picasso.Picasso

import timber.log.Timber

class PicassoCache(private val context: Context) {

    init {
        val builder = Picasso.Builder(context)
        builder.downloader(OkHttpDownloader(context, Integer.MAX_VALUE.toLong()))
        val picasso = builder.build()
        Picasso.setSingletonInstance(picasso)
    }

    fun getImage(imageView: ImageView, url: String) {
        Picasso
                .with(context)
                .load(url)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        Timber.i("Image fetched")
                    }

                    override fun onError() {
                        Picasso
                                .with(context)
                                .load(url)
                                .into(imageView)
                    }
                })
    }

}

