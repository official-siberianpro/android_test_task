package com.androidtesttask.events;

/**
 * Date: 18.03.2016
 * Time: 23:19
 *
 * @author Artem Zalevskiy
 */
public class ClickEvent {

    private final String mNameGroup;
    private final String mNameItem;

    public ClickEvent(final String nameGroup, final String nameItem) {
        mNameGroup = nameGroup;
        mNameItem = nameItem;
    }

    public String getNameGroup() {
        return mNameGroup;
    }

    public String getNameItem() {
        return mNameItem;
    }
}
