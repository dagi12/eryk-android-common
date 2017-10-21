package pl.edu.amu.wmi.erykandroidcommon.service;

import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import timber.log.Timber

public class PicassoCache {

    private final Context context;

    public PicassoCache(Context context) {
        this.context = context;
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttpDownloader(context, Integer.MAX_VALUE));
        Picasso picasso = builder.build();
        Picasso.setSingletonInstance(picasso);
    }

    public void getImage(final ImageView imageView, final String url) {
        Picasso
                .with(context)
                .load(url)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Timber.i("Image fetched");
                    }

                    @Override
                    public void onError() {
                        Picasso
                                .with(context)
                                .load(url)
                                .into(imageView);
                    }
                });
    }

}

