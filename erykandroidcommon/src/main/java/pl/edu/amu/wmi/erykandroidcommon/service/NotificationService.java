package pl.edu.amu.wmi.erykandroidcommon.service;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.session.MediaSessionCompat;

import java.lang.reflect.ParameterizedType;

import io.reactivex.Single;
import lombok.Getter;
import lombok.NonNull;

/**
 * A centralized service for displaying {@link android.app.Notification}s.
 *
 * @author Cezary Krzyżanowski <cezary.krzyzanowski@247.codes> on 16.01.2017.
 */
public abstract class NotificationService<T extends Activity> {

    private final NotificationManager notificationManager;

    private final Context context;

    @Getter(lazy = true)
    private final MediaSessionCompat mediaSession = new MediaSessionCompat(context, getClass().getSimpleName());

    private final int launcherMipmap;

    @DrawableRes
    private final int notificationDrawable;

    private final Class<T> mainActivityClass;

    private static final String DEFAULT_CHANNEL = "default";

    private int notificationId = 0;

    @SuppressWarnings("unchecked")
    public NotificationService(Context context, @DrawableRes int notificationDrawable, int launcherMipmap) {
        this.context = context;
        this.notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        this.mainActivityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        this.notificationDrawable = notificationDrawable;
        this.launcherMipmap = launcherMipmap;
    }

    @NonNull
    private PendingIntent getPendingIntent() {
        Intent notifyIntent = new Intent(context, mainActivityClass);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(context, 0,
                notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    /**
     * Send a notification based on the given {@code builder}.
     * <p>
     * Fills empty fields in the {@code builder} with default values. Constructs a
     * {@link Notification} from the {@link android.support.v4.app.NotificationCompat.Builder} and
     * sends it to be displayed.
     * <p>
     * If the caller hasn't set something, then this function will use defaults.
     *
     * @param builder {@link android.support.v4.app.NotificationCompat.Builder} with pre-filled fields.
     * @return Single<Integer> The displayed notification's id.
     */
    private Single<Integer> displayNotification(@NonNull NotificationCompat.Builder builder) {
        // All other needs to be set after the notification has been build.
        Notification notification = defaultNotification(defaultNotificationBuilder(builder).build());

        // Finally send the notification.
        notificationManager.notify(++notificationId, notification);

        return Single.just(notificationId);
    }

    private NotificationCompat.Builder defaultNotificationBuilder(NotificationCompat.Builder builder) {
        builder.setCategory(NotificationCompat.CATEGORY_EVENT);
        final Bundle extras = builder.getExtras();
        // If no small icon, set the default launcher icon.
        if (!extras.containsKey(NotificationCompat.EXTRA_SMALL_ICON)) {
            builder.setSmallIcon(notificationDrawable);
        }
        // If no big icon, then set the default launcher icon.
        if (!extras.containsKey(NotificationCompat.EXTRA_LARGE_ICON)) {
            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), launcherMipmap));
        }
        // If no style set, then make it the BigTextStyle.
        if (builder.mStyle == null) {
            builder.setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(builder.mContentText)
                    .setSummaryText(builder.mContentInfo));
        }
        return builder;
    }

    private Notification defaultNotification(Notification notification) {
        // Set the default notification clicked Intent if not set.
        if (notification.contentIntent == null) {
            notification.contentIntent = getPendingIntent();
        }
        return notification;
    }

    /**
     * Display a {@link Notification} with the given {@code title} and {@code message}.
     *
     * @param title   String The title of the {@link Notification}.
     * @param message String the message of the {@link Notification}.
     * @return Single<Integer> The displayed notification's id.
     */
    public Single<Integer> showMessage(@NonNull String title, @Nullable String message) {
        return showMessageWithInfo(title, message, null);
    }

    /**
     * Display a {@link Notification} with the given {@code title} and {@code message}.
     *
     * @param title   String The title of the {@link Notification}.
     * @param message String the message of the {@link Notification}.
     * @param info    String with an additional info in the {@link Notification}. Can be null.
     */
    private Single<Integer> showMessageWithInfo(@NonNull String title, @Nullable String message, String info) {
        return displayNotification(new NotificationCompat.Builder(context, DEFAULT_CHANNEL)
                .setContentTitle(title)
                .setContentText(message)
                .setContentInfo(info));
    }

}
