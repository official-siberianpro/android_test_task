package com.androidtesttask.ui.activities;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.alexvasilkov.android.commons.texts.Fonts;
import com.alexvasilkov.events.EventsActivity;
import com.mig35.injectorlib.utils.inject.Injector;

/**
 * Date: 18.03.2016
 * Time: 18:55
 *
 * @author Artem Zalevskiy
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Injector mInjector;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        mInjector = Injector.init(this);

        mInjector.applyOnActivityCreate(this, savedInstanceState);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        EventsActivity.onCreate(this, savedInstanceState);
        final int layoutResource = getLayoutResourceId();
        if (0 != layoutResource) {
            setContentView(layoutResource);
        }
    }

    protected abstract int getLayoutResourceId();

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        mInjector.applyOnActivityContentChange(this);
    }

    @Override
    protected void onPostCreate(final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Fonts.apply(getWindow().getDecorView());
    }


    @Override
    protected void onResume() {
        super.onResume();

        EventsActivity.onResume(this);
    }

    @Override
    protected void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);

        EventsActivity.onSaveInstanceState(this, outState);
        mInjector.applyOnActivitySaveInstanceState(this, outState);
    }

    @Override
    protected void onDestroy() {
        EventsActivity.onDestroy(this);

        mInjector.applyOnActivityDestroy(this);

        super.onDestroy();
    }

    public boolean showDialog(final DialogFragment dialogFragment) {
        return showDialog(dialogFragment, null);
    }

    public boolean showDialog(final DialogFragment dialogFragment, final String tag) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (null == tag || null == getFragmentManager().findFragmentByTag(tag)) {
            ft.add(dialogFragment, tag);
            ft.commit();
            return true;
        }
        return false;
    }

    public void hideDialog(final String tag) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        final Fragment fragment = getFragmentManager().findFragmentByTag(tag);
        if (null != fragment) {
            ft.remove(fragment);
            ft.commitAllowingStateLoss();
        }
    }
}
