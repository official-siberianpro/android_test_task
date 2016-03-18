package com.androidtesttask.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 18.03.2016
 * Time: 19:14
 *
 * @author Artem Zalevskiy
 */
public class Group implements Serializable {

    private static final long serialVersionUID = -5858400289495856081L;

    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;

    private final List<Item> mItems = new ArrayList<>();

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void addItem(final Item item) {
        if (null == item || mItems.contains(item)) {
            return;
        }
        mItems.add(item);
    }

    public List<Item> getItems() {
        return mItems;
    }
}
