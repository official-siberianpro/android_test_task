package com.androidtesttask.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 18.03.2016
 * Time: 19:21
 *
 * @author Artem Zalevskiy
 */
public class JsonData implements Serializable {

    private static final long serialVersionUID = 235583141128409704L;
    @SerializedName("items")
    private List<Item> mItems;
    @SerializedName("groups")
    private List<Group> mGroups;

    public List<Group> getGroups() {
        final HashMap<Long, Group> data = new HashMap<>();
        for (final Group group : mGroups) {
            for (final Item item : mItems) {
                if (group.getId() == item.getGroupId()) {
                    if (data.containsKey(group.getId())) {
                        final Group groupAdded = data.get(group.getId());
                        groupAdded.addItem(item);
                        data.put(group.getId(), group);
                    } else {
                        group.addItem(item);
                        data.put(group.getId(), group);
                    }
                }
            }
        }
        return new ArrayList<>(data.values());
    }
}
