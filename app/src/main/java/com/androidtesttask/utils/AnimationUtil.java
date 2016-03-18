package com.androidtesttask.utils;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Date: 18.03.2016
 * Time: 21:38
 *
 * @author Artem Zalevskiy
 */
public final class AnimationUtil {

    private static final int DURATION = 1000;
    private static final float FLOAT_VALUE = 360f;

    private AnimationUtil() {
    }

    public static void rotate(final View view, final float from, final float to) {
        final ObjectAnimator animation = ObjectAnimator.ofFloat(view, "rotation", from, to);
        animation.setDuration(DURATION);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
    }

    public static void rotate(final View view, final boolean flag) {
        rotate(view, flag ? FLOAT_VALUE : 0f, flag ? 0f : FLOAT_VALUE);
    }
}
