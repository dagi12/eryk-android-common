package pl.edu.amu.wmi.erykandroidcommon.ui.spinner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import pl.edu.amu.wmi.erykandroidcommon.R;


public class NumberSpinnerAdapter extends ArrayAdapter<Integer> {

    public NumberSpinnerAdapter(@NonNull Context context, int count) {
        super(context, R.layout.spinner_dropdown_item, getItems(count));
    }

    private static List<Integer> getItems(int count) {
        List<Integer> integers = new ArrayList<>();
        for (int i = 1; i <= count; ++i) {
            integers.add(i);
        }
        return integers;
    }

}
