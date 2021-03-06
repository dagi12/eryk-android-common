package pl.edu.amu.wmi.erykandroidcommon.rx

import android.content.Context
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.widget.EditText
import android.widget.TextView
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import pl.edu.amu.wmi.erykandroidcommon.R

/**
 * Utility class for creating reactive AlertDialogs.
 *
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 23.06.2017.
 */
object ReactiveDialogs {

    fun instantOkDialog(context: Context,
                        @StringRes title: Int,
                        @StringRes message: Int) {
        okDialog(context, title, message).show()
    }

    fun instantOkDialog(context: Context,
                        title: String,
                        message: String) {
        val dialog = okDialog(context, title, message).create()
        dialog.show()
        dialog.findViewById<TextView>(android.R.id.message)?.gravity = Gravity.CENTER
    }

    fun instantOkDialog(context: Context, @StringRes titleId: Int) {
        okDialog(context, titleId).show()
    }

    private fun dialogBuilder(context: Context,
                              title: String,
                              message: String): AlertDialog.Builder =
            AlertDialog
                    .Builder(context)
                    .setTitle(title)
                    .setMessage(message)

    private fun okDialog(context: Context,
                         title: String,
                         message: String): AlertDialog.Builder = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }

    fun errorDialog(context: Context, message: String): AlertDialog? =
            AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.error))
                    .setMessage(message)
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.ok) { dialogInterface, _ ->
                        dialogInterface.dismiss()
                    }
                    .show()

    private fun okDialog(context: Context, @StringRes titleId: Int): AlertDialog.Builder =
            AlertDialog.Builder(context)
                    .setTitle(context.getString(titleId))
                    .setPositiveButton(
                            android.R.string.ok
                    ) { dialogInterface, _ -> dialogInterface.dismiss() }

    private fun okDialog(context: Context,
                         @StringRes titleId: Int,
                         @StringRes message: Int): AlertDialog.Builder = AlertDialog.Builder(context)
            .setTitle(context.getString(titleId))
            .setMessage(context.getString(message))
            .setPositiveButton(
                    android.R.string.ok
            ) { dialogInterface, _ -> dialogInterface.dismiss() }

    fun okDialog(context: Context,
                 title: String,
                 message: String,
                 buttonCallback: ButtonCallback): AlertDialog.Builder {
        return AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(
                        android.R.string.ok
                ) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    buttonCallback.callback()
                }
    }

    fun infoDialog(context: Context, msg: String) {
        okDialog(context, "Info", msg).show()
    }

    fun confirmationDialog(context: Context,
                           title: Int,
                           message: Int,
                           vararg messageArgs: Any): Single<Boolean> {
        val resources = context.resources
        return confirmationDialog(context, resources.getString(title),
                resources.getString(message, *messageArgs))
    }


    private fun confirmationDialog(context: Context,
                                   title: String,
                                   message: String,
                                   @StringRes okResource: Int = android.R.string.ok,
                                   @StringRes cancelResource: Int = android.R.string.cancel): Single<Boolean> =
            Single.create<Boolean> { emitter ->
                val ad = dialogBuilder(context, title, message).setPositiveButton(
                        okResource) { _, _ -> emitter.onSuccess(true) }
                        .setNegativeButton(cancelResource) { _, _ -> emitter.onSuccess(false) }
                        .create()
                // cleaning up
                emitter.setDisposable(Disposables.fromAction({ ad.dismiss() }))
                ad.show()
            }.subscribeOn(AndroidSchedulers.mainThread())

    fun confirmationDialog(context: Context,
                           @StringRes messageId: Int,
                           @StringRes okResource: Int = android.R.string.ok,
                           @StringRes cancelResource: Int = android.R.string.cancel): Completable = Completable.create { emitter ->
        val ad = AlertDialog.Builder(context).setMessage(context.getString(messageId)).setPositiveButton(
                okResource) { _, _ -> emitter.onComplete() }
                .setNegativeButton(cancelResource) { _, _ -> }
                .create()
        emitter.setDisposable(Disposables.fromAction({ ad.dismiss() }))
        ad.show()
    }.subscribeOn(AndroidSchedulers.mainThread())

    fun confirmationDialog(context: Context): Completable = Completable.defer {
        Completable.create { emitter ->
            AlertDialog.Builder(context).setTitle(R.string.are_you_sure)
                    .setPositiveButton(R.string.yes_delete
                    ) { _, _ -> emitter.onComplete() }
                    .setNegativeButton(android.R.string.no
                    ) { _, _ -> Completable.never() }
                    .create()
                    .show()
        }
    }

    fun textDialog(
            context: Context,
            title: String,
            message: String): Single<String> = Single.create { emitter ->
        val editText = EditText(context)
        AlertDialog
                .Builder(context)
                .setMessage(message)
                .setTitle(title)
                .setView(editText)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    emitter.onSuccess(editText.text.toString())
                }
                .create()
                .show()
    }

    fun textDialog(context: Context, title: String?, message: String, text: String?): Single<String> = Single.create { emitter ->
        val editText = EditText(context)
        editText.setText(text, TextView.BufferType.EDITABLE)
        AlertDialog
                .Builder(context)
                .setMessage(message)
                .setTitle(title)
                .setView(editText)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    emitter.onSuccess(editText.text.toString())
                }
                .create()
                .show()
    }
}

interface ButtonCallback {
    fun callback()
}

