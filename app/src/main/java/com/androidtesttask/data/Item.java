package com.androidtesttask.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Date: 18.03.2016
 * Time: 19:22
 *
 * @author Artem Zalevskiy
 */
public class Item implements Comparable<Item>, Serializable {

    private static final long serialVersionUID = -7269854263546221030L;

    @SerializedName("id")
    private long mId;
    @SerializedName("group_id")
    private long mGroupId;
    @SerializedName("name")
    private String mName;

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public long getGroupId() {
        return mGroupId;
    }

    @SuppressWarnings("NonFinalFieldReferenceInEquals")
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }

        final Item item = (Item) o;

        return mId == item.mId;
    }

    @SuppressWarnings("NonFinalFieldReferencedInHashCode")
    @Override
    public int hashCode() {
        return (int) (mId ^ (mId >>> 32));
    }

    @Override
    public int compareTo(@NonNull final Item item) {
        return compare(getId(), item.getId());
    }

    public static int compare(final long lhs, final long rhs) {
        return lhs < rhs ? -1 : (lhs == rhs ? 0 : 1);
    }
}
