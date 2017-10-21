package pl.edu.amu.wmi.erykandroidcommon.service;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@softra.pl> on 18.10.17.
 */
public class UserService<T extends Token, S> {

    private final Class<S> startActivity;

    private final Context context;

    @Getter
    @Setter
    private T user;

    public UserService(Application application, Class<S> startActivity) {
        this.context = application;
        this.startActivity = startActivity;
    }

    public void hardLogout() {
        this.user = null;
        Intent signOutIntent = new Intent(context, startActivity);
        signOutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(signOutIntent);
    }

    public boolean isSignedIn() {
        return user != null;
    }

}
