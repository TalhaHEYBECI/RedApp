package com.dgpays.movieapi.view.fragment;

import android.os.Build;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class BaseFragment extends Fragment {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void hideSoftKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
        }
    }
}
