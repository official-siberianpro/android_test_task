package com.androidtesttask.ui.dialogs;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.mig35.injectorlib.utils.inject.Injector;

public abstract class BaseDialog extends DialogFragment {

    private Injector mInjector;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        mInjector = Injector.init(this);

        super.onCreate(savedInstanceState);

        mInjector.applyOnFragmentCreate(this, savedInstanceState);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mInjector.applyOnFragmentViewCreated(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);

        mInjector.applyOnFragmentSaveInstanceState(this, outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mInjector.applyOnFragmentDestroyView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void show(final FragmentManager manager, final String tag) {
        final FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

}
