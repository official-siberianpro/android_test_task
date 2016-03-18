package com.androidtesttask.ui.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexvasilkov.events.Events;
import com.androidtesttask.R;
import com.androidtesttask.data.Group;
import com.androidtesttask.data.Item;
import com.androidtesttask.events.ClickEvent;

import java.util.List;

/**
 * Date: 18.03.2016
 * Time: 18:34
 *
 * @author Artem Zalevskiy
 */
public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.GroupViewHolder> {

    private final List<Group> mGroups;
    private final Activity mActivity;

    public GroupsAdapter(final Activity activity, final List<Group> groups) {
        mGroups = groups;
        mActivity = activity;
    }

    @Override
    public GroupViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GroupViewHolder holder, final int position) {
        final Group group = mGroups.get(position);
        holder.mNameView.setText(group.getName());
        holder.mNameView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                Events.create(R.id.event_long_click).data(0
                        == position % 2).postTo(mActivity);
                return false;
            }
        });
        addView(holder.mContainer, group.getName(), group.getItems());
    }

    private void addView(final ViewGroup viewGroup, final String groupName, final Iterable<Item> itemList) {
        for (final Item item : itemList) {
            final View view = View.inflate(viewGroup.getContext(), R.layout.item_view, null);
            final TextView textView = (TextView) view.findViewById(R.id.tv_name_id);
            textView.setText(item.getName());
            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View v) {
                    Events.create(R.id.event_click).data(new ClickEvent(groupName, item.getName())).postTo(mActivity);
                    return false;
                }
            });
            viewGroup.addView(view);
        }
    }

    @Override
    public int getItemCount() {
        return null == mGroups ? 0 : mGroups.size();
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder {

        private final TextView mNameView;
        private final ViewGroup mContainer;

        public GroupViewHolder(final View itemView) {
            super(itemView);
            mContainer = (ViewGroup) itemView.findViewById(R.id.ll_container_id);
            mNameView = (TextView) itemView.findViewById(R.id.tv_name_id);
            mNameView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    mContainer.setVisibility(mContainer.isShown() ? View.GONE : View.VISIBLE);
                }
            });
        }
    }
}