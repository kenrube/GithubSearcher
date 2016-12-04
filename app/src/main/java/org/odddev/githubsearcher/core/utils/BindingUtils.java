package org.odddev.githubsearcher.core.utils;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;

import org.odddev.githubsearcher.R;

/**
 * @author kenrube
 * @since 02.12.16
 */

public class BindingUtils {

    private BindingUtils() {
    }

    @BindingAdapter("onClick")
    public static void bindOnClick(View view, final Runnable runnable) {
        view.setOnClickListener(v -> runnable.run());
    }

    @BindingAdapter({"srcUri", "isCircle", "placeholder"})
    public static void bindSrcUri(ImageView imageView, final String uri, final boolean isCircle,
                                  final Drawable placeholder) {
        DrawableRequestBuilder<String> builder = Glide
                .with(imageView.getContext())
                .load(uri)
                .placeholder(placeholder)
                .error(placeholder);
        if (isCircle) {
            builder = builder.transform(new CircleTransform(imageView.getContext()));
        }
        builder.into(imageView);
    }

    @BindingAdapter("font")
    public static void bindFont(TextView textView, final String fontName) {
        String robotoMedium = textView.getContext().getString(R.string.roboto_medium);
        if (fontName.equals(robotoMedium) && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            textView.setTypeface(Typeface.createFromAsset(
                    textView.getContext().getAssets(), "fonts/" + robotoMedium + ".ttf"));
        } else {
            textView.setTypeface(Typeface.create(fontName, Typeface.NORMAL));
        }
    }

    @BindingAdapter({"fromXDelta", "fromYDelta"})
    public static void bindFabLayoutParams(FloatingActionButton fab, final double fromXDelta,
                                           final double fromYDelta) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab.getLayoutParams();
        layoutParams.rightMargin += (int) (fab.getWidth() * fromXDelta);
        layoutParams.bottomMargin += (int) (fab.getHeight() * fromYDelta);
        fab.setLayoutParams(layoutParams);

        fab.setClickable(fromXDelta > 0);
    }

    @BindingConversion
    public static int convertConditionToVisibility(boolean condition) {
        return condition ? View.VISIBLE : View.GONE;
    }
}
