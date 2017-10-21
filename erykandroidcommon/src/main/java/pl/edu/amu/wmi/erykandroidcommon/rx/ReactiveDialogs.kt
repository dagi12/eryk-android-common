package pl.edu.amu.wmi.erykandroidcommon.rx

import android.content.Context
import android.content.res.Resources
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import lombok.NonNull
import pl.edu.amu.wmi.erykandroidcommon.R

/**
 * Utility class for creating reactive AlertDialogs.
 *
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 23.06.2017.
 */
internal object ReactiveDialogs {

    fun instantOkDialog(@NonNull context: Context,
                        @StringRes title: Int,
                        @StringRes message: Int) {
        okDialog(context, title, message).show()
    }

    fun instantOkDialog(@NonNull context: Context,
                        @NonNull title: String,
                        @NonNull message: String) {
        okDialog(context, title, message).show()
    }

    fun instantOkDialog(@NonNull context: Context, @StringRes titleId: Int) {
        okDialog(context, titleId).show()
    }

    private fun dialogBuilder(@NonNull context: Context,
                              @NonNull title: String,
                              @NonNull message: String): AlertDialog.Builder {
        return AlertDialog.Builder(context).setTitle(title).setMessage(message)
    }

    fun okDialog(@NonNull context: Context,
                 @NonNull title: String,
                 @NonNull message: String): AlertDialog.Builder {
        return AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(
                        android.R.string.ok
                ) { dialogInterface, i -> dialogInterface.dismiss() }
    }

    private fun okDialog(@NonNull context: Context, @StringRes titleId: Int): AlertDialog.Builder {
        return AlertDialog.Builder(context)
                .setTitle(context.getString(titleId))
                .setPositiveButton(
                        android.R.string.ok
                ) { dialogInterface, i -> dialogInterface.dismiss() }
    }

    fun okDialog(@NonNull context: Context,
                 @StringRes titleId: Int,
                 @StringRes message: Int): AlertDialog.Builder {
        return AlertDialog.Builder(context)
                .setTitle(context.getString(titleId))
                .setMessage(context.getString(message))
                .setPositiveButton(
                        android.R.string.ok
                ) { dialogInterface, i -> dialogInterface.dismiss() }
    }

    fun okDialog(@NonNull context: Context,
                 @NonNull title: String,
                 @NonNull message: String,
                 @NonNull buttonCallback: ButtonCallback): AlertDialog.Builder {
        return AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(
                        android.R.string.ok
                ) { dialogInterface, i ->
                    dialogInterface.dismiss()
                    buttonCallback.callback()
                }
    }

    fun confirmationDialog(@NonNull context: Context,
                           title: Int,
                           message: Int,
                           vararg messageArgs: Any): Single<Boolean> {
        val resources = context.resources
        return confirmationDialog(context, resources.getString(title),
                resources.getString(message, *messageArgs))
    }


    @JvmOverloads
    fun confirmationDialog(@NonNull context: Context,
                           @NonNull title: String,
                           @NonNull message: String,
                           @StringRes okResource: Int = android.R.string.ok,
                           @StringRes cancelResource: Int = android.R.string.cancel): Single<Boolean> {

        return Single.create<Boolean> { emitter ->
            val ad = dialogBuilder(context, title, message).setPositiveButton(
                    okResource) { dialog, which -> emitter.onSuccess(true) }
                    .setNegativeButton(cancelResource) { dialog, which -> emitter.onSuccess(false) }
                    .create()
            // cleaning up
            emitter.setDisposable(Disposables.fromAction(Action { ad.dismiss() }))
            ad.show()
        }.subscribeOn(AndroidSchedulers.mainThread())
    }

    fun confirmationDialog(@NonNull context: Context): Completable {
        return Completable.defer {
            Completable.create { emitter ->
                AlertDialog.Builder(context).setTitle(R.string.are_you_sure)
                        .setPositiveButton(R.string.yes_delete
                        ) { dialogInterface, i -> emitter.onComplete() }
                        .setNegativeButton(android.R.string.no
                        ) { dialogInterface, i -> Completable.never() }
                        .create()
                        .show()
            }
        }
    }


    interface ButtonCallback {
        fun callback()
    }

}
