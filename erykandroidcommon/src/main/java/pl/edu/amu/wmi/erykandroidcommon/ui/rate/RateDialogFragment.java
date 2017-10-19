package pl.edu.amu.wmi.erykandroidcommon.ui.rate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import butterknife.ButterKnife;
import pl.edu.amu.wmi.erykandroidcommon.R;
import pl.edu.amu.wmi.erykandroidcommon.exception.AdapterLackException;
import pl.edu.amu.wmi.erykandroidcommon.ui.spinner.NumberSpinnerAdapter;

public class RateDialogFragment extends DialogFragment {

    //    @BindView(R.id.rate_spinner)
    Spinner rateSpinner;

    private static final String VOTE_TITLE_PARAM = "VOTE_TITLE";
    private static final String MAX_POINTS_PARAM = "MAX_POINTS";
    private RateResultAdapter adapter;

    public static RateDialogFragment getInstance(String message, Integer maxPoints) {
        RateDialogFragment rateDialogFragment = new RateDialogFragment();
        Bundle args = new Bundle();
        args.putString(VOTE_TITLE_PARAM, message);
        args.putInt(MAX_POINTS_PARAM, maxPoints);
        rateDialogFragment.setArguments(args);
        return rateDialogFragment;
    }

    @SuppressWarnings("unchecked")
    private void initialize(Context context) {
        if (context instanceof RateResultAdapter) {
            adapter = (RateResultAdapter) context;
        } else {
            throw new AdapterLackException(getActivity(), RateResultAdapter.class);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(getActivity());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                R.style.AppTheme_MyAlertDialogStyle);
        builder.setTitle(getArguments().getString(VOTE_TITLE_PARAM))
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // niepotrzebne
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.processVote((int) rateSpinner.getSelectedItem());
                    }
                });

        ViewGroup parent = (ViewGroup) getActivity().findViewById(android.R.id.content);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_rate_dialog, parent, false);
        builder.setView(view);
        Dialog dialog = builder.create();
        ButterKnife.bind(this, view);
        rateSpinner.setAdapter(new NumberSpinnerAdapter(getActivity(),
                getArguments().getInt(MAX_POINTS_PARAM)));
        return dialog;
    }

    public interface RateResultAdapter {
        void processVote(final int vote);
    }

}
