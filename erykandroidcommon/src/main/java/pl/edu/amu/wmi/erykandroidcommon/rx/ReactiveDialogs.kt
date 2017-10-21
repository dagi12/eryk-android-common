package pl.edu.amu.wmi.erykandroidcommon.rx;

import android.support.annotation.Nullable
import android.support.annotation.StringRes
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.Disposables
import lombok.NonNull
import pl.edu.amu.wmi.erykandroidcommon.R

/**
 * Utility class for creating reactive AlertDialogs.
 *
 * @author Eryk Mariankowski <eryk.mariankowski@softra.pl> on 23.06.2017.
 */
class ReactiveDialogs {

    private ReactiveDialogs() {

    }

    public static void instantOkDialog(@NonNull Context context,
                                       @StringRes int title,
                                       @StringRes int message) {
        okDialog(context, title, message).show();
    }

    public static void instantOkDialog(@NonNull Context context,
                                       @NonNull String title,
                                       @NonNull String message) {
        okDialog(context, title, message).show();
    }

    public static void instantOkDialog(@NonNull Context context, @StringRes int titleId) {
        okDialog(context, titleId).show();
    }

    private static AlertDialog.Builder dialogBuilder(@NonNull final Context context,
                                                     @NonNull final String title,
                                                     @NonNull final String message) {
        return new AlertDialog.Builder(context).setTitle(title).setMessage(message);
    }

    public static AlertDialog.Builder okDialog(@NonNull final Context context,
                                               @NonNull final String title,
                                               @NonNull final String message) {
        return new AlertDialog
                .Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(
                        android.R.string.ok,
                        (dialogInterface, i) -> dialogInterface.dismiss()
                );
    }

    private static AlertDialog.Builder okDialog(@NonNull final Context context, @StringRes int titleId) {
        return new AlertDialog
                .Builder(context)
                .setTitle(context.getString(titleId))
                .setPositiveButton(
                        android.R.string.ok,
                        (dialogInterface, i) -> dialogInterface.dismiss()
                );
    }

    public static AlertDialog.Builder okDialog(@NonNull final Context context,
                                               @StringRes final int titleId,
                                               @StringRes final int message) {
        return new AlertDialog
                .Builder(context)
                .setTitle(context.getString(titleId))
                .setMessage(context.getString(message))
                .setPositiveButton(
                        android.R.string.ok,
                        (dialogInterface, i) -> dialogInterface.dismiss()
                );
    }

    public static AlertDialog.Builder okDialog(@NonNull final Context context,
                                               @NonNull final String title,
                                               @NonNull final String message,
                                               @NonNull ButtonCallback buttonCallback) {
        return new AlertDialog
                .Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(
                        android.R.string.ok,
                        (dialogInterface, i) -> {
                            dialogInterface.dismiss();
                            buttonCallback.callback();
                        }
                );
    }

    public static Single<Boolean> confirmationDialog(@NonNull final Context context,
                                                     final int title,
                                                     final int message,
                                                     @Nullable final Object... messageArgs) {
        final Resources resources = context.getResources();
        return confirmationDialog(context, resources.getString(title),
                resources.getString(message, messageArgs));
    }

    public static Single<Boolean> confirmationDialog(@NonNull final Context context,
                                                     @NonNull final String title,
                                                     @NonNull final String message) {
        return confirmationDialog(context, title, message, android.R.string.ok,
                android.R.string.cancel);
    }


    public static Single<Boolean> confirmationDialog(@NonNull final Context context,
                                                     @NonNull final String title,
                                                     @NonNull final String message,
                                                     @StringRes int okResource,
                                                     @StringRes int cancelResource) {

        return Single.<Boolean>create(emitter -> {
            final AlertDialog ad = dialogBuilder(context, title, message).setPositiveButton(
                    okResource, (dialog, which) -> emitter.onSuccess(true))
                    .setNegativeButton(cancelResource, (dialog, which) -> emitter.onSuccess(false))
                    .create();
            // cleaning up
            emitter.setDisposable(Disposables.fromAction(ad::dismiss));
            ad.show();
        }).subscribeOn(AndroidSchedulers.mainThread());
    }

    public static Completable confirmationDialog(@NonNull final Context context) {
        return Completable.defer(() -> Completable.create(
                emitter -> new AlertDialog.Builder(context).setTitle(R.string.are_you_sure)
                        .setPositiveButton(R.string.yes_delete,
                                (dialogInterface, i) -> emitter.onComplete())
                        .setNegativeButton(android.R.string.no,
                                (dialogInterface, i) -> Completable.never())
                        .create()
                        .show()));
    }


    public interface ButtonCallback {
        void callback();
    }

}
