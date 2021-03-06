package pl.edu.amu.wmi.erykandroidcommon.service

import android.app.Activity
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.annotation.DrawableRes
import android.support.v4.app.NotificationCompat
import android.support.v4.media.session.MediaSessionCompat
import io.reactivex.Single

/**
 * A centralized service for displaying [android.app.Notification]s.
 *
 * @author Cezary Krzyżanowski <cezary.krzyzanowski></cezary.krzyzanowski>@247.codes> on 16.01.2017.
 */
abstract class NotificationService<T : Activity>(private val context: Context,
    @param:DrawableRes @field:DrawableRes
    private val notificationDrawable: Int,
    private val launcherMipmap: Int,
    private val mainActivityClass: Class<T>) {

    private val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val mediaSession = MediaSessionCompat(context, javaClass.simpleName)

    private var notificationId = 0

    private val pendingIntent: PendingIntent
        get() {
            val notifyIntent = Intent(context, mainActivityClass)
            notifyIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            return PendingIntent.getActivity(context, 0,
                notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

    /**
     * Send a notification based on the given `builder`.
     *
     *
     * Fills empty fields in the `builder` with default values. Constructs a
     * [Notification] from the [android.support.v4.app.NotificationCompat.Builder] and
     * sends it to be displayed.
     *
     *
     * If the caller hasn't set something, then this function will use defaults.
     *
     * @param builder [android.support.v4.app.NotificationCompat.Builder] with pre-filled fields.
     * @return Single<Integer> The displayed notification's id.
    </Integer> */
    private fun displayNotification(builder: NotificationCompat.Builder, message: String?, info: String?): Single<Int> {
        // All other needs to be set after the notification has been build.
        val notification = defaultNotification(
            defaultNotificationBuilder(
                builder,
                message,
                info
            ).build()
        )
        // Finally send the notification.
        notificationManager.notify(++notificationId, notification)

        return Single.just(notificationId)
    }

    private fun defaultNotificationBuilder(builder: NotificationCompat.Builder, message: String?, info: String?): NotificationCompat.Builder {
        builder.setCategory(NotificationCompat.CATEGORY_EVENT)
        val extras = builder.extras
        // If no small icon, set the default launcher icon.
        if (!extras.containsKey(NotificationCompat.EXTRA_SMALL_ICON)) {
            builder.setSmallIcon(notificationDrawable)
        }
        // If no big icon, then set the default launcher icon.
        if (!extras.containsKey(NotificationCompat.EXTRA_LARGE_ICON)) {
            builder.setLargeIcon(BitmapFactory.decodeResource(context.resources, launcherMipmap))
        }
        // If no style set, then make it the BigTextStyle.
        builder.setStyle(NotificationCompat.BigTextStyle()
            .bigText(message)
            .setSummaryText(info))

        return builder
    }

    private fun defaultNotification(notification: Notification): Notification {
        // Set the default notification clicked Intent if not set.
        if (notification.contentIntent == null) {
            notification.contentIntent = pendingIntent
        }
        return notification
    }

    /**
     * Display a [Notification] with the given `title` and `message`.
     *
     * @param title   String The title of the [Notification].
     * @param message String the message of the [Notification].
     * @return Single<Integer> The displayed notification's id.
    </Integer> */
    fun showMessage(title: String, message: String?): Single<Int> = showMessageWithInfo(title, message, null)

    /**
     * Display a [Notification] with the given `title` and `message`.
     *
     * @param title   String The title of the [Notification].
     * @param message String the message of the [Notification].
     * @param info    String with an additional info in the [Notification]. Can be null.
     */
    private fun showMessageWithInfo(title: String, message: String?, info: String?): Single<Int> {
        return displayNotification(NotificationCompat.Builder(context, DEFAULT_CHANNEL)
            .setContentTitle(title)
            .setContentText(message)
            .setContentInfo(info),
            message,
            info
        )
    }

    companion object {

        private val DEFAULT_CHANNEL = "default"
    }
}
