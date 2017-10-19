package pl.edu.amu.wmi.erykandroidcommon.service;

import android.app.Application;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

public class PicassoCache {

    private final Application application;

    private static final String TAG = PicassoCache.class.getName();

    public PicassoCache(Application application) {
        this.application = application;
        Picasso.Builder builder = new Picasso.Builder(application);
        builder.downloader(new OkHttpDownloader(application, Integer.MAX_VALUE));
        Picasso picasso = builder.build();
        Picasso.setSingletonInstance(picasso);
    }

    public void getImage(final ImageView imageView, final String url) {
        Picasso
                .with(application)
                .load(url)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.i(TAG, "Image fetched");
                    }

                    @Override
                    public void onError() {
                        Picasso
                                .with(application)
                                .load(url)
                                .into(imageView);
                    }
                });
    }

}

