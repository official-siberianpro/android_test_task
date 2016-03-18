package com.androidtesttask.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alexvasilkov.android.commons.utils.GsonHelper;
import com.alexvasilkov.events.Event;
import com.alexvasilkov.events.Events;
import com.androidtesttask.R;
import com.androidtesttask.data.JsonData;
import com.androidtesttask.events.ClickEvent;
import com.androidtesttask.ui.adapters.GroupsAdapter;
import com.androidtesttask.ui.dialogs.MessageDialog;
import com.androidtesttask.utils.AnimationUtil;
import com.mig35.injectorlib.utils.inject.InjectSavedState;
import com.mig35.injectorlib.utils.inject.InjectView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.recycler_view)
    private RecyclerView mRecycleView;
    @InjectSavedState
    private JsonData mJsonData;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null == mJsonData) {
            mJsonData = getData();
        }

        initList();
    }

    private JsonData getData() {
        try {
            final InputStream input = getAssets().open("data/data.json");
            final Reader reader = new InputStreamReader(input, "UTF-8");
            return GsonHelper.get().fromJson(reader, JsonData.class);
        } catch (final Exception e) {
            Log.e(getClass().getName(), e.getMessage(), e);
        }
        return null;
    }

    private void initList() {
        if (null == mJsonData) {
            return;
        }
        mRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycleView.setAdapter(new GroupsAdapter(this, mJsonData.getGroups()));
    }

    @Events.Receiver(R.id.event_click)
    private void onReceiverClick(final Event event) {
        final ClickEvent clickEvent = event.getData();
        MessageDialog.build(this).positive(android.R.string.ok).message(clickEvent.getNameItem()).title(clickEvent.getNameGroup()).show();
    }

    @Events.Receiver(R.id.event_long_click)
    private void onReceiverLongClick(final Event event) {
        final boolean flag = event.getData();
        AnimationUtil.rotate(mRecycleView, flag);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

}
