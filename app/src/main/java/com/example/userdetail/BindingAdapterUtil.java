package com.example.userdetail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class BindingAdapterUtil {


    @BindingAdapter("mutableText")
    public static void setMutableText(final EditText view, final MutableLiveData<String> text) {
        ViewExtension viewExtension = new ViewExtension(view);
        LifecycleOwner lifecycleOwner = viewExtension.getLifecycleOwner();
        if (lifecycleOwner != null && text != null) {
            text.observe(lifecycleOwner, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {

                    view.setText(text.getValue());
                }
            });
        }

    }


    @BindingAdapter("EditableText")
    public static void setEditableText(final EditText view, final MutableLiveData<Boolean> text) {
        ViewExtension viewExtension = new ViewExtension(view);
        LifecycleOwner lifecycleOwner = viewExtension.getLifecycleOwner();
        if (lifecycleOwner != null && text != null) {
            text.observe(lifecycleOwner, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean s) {
                    view.setEnabled(s);
                    view.setClickable(s);
                    view.setCursorVisible(s);
                }
            });
        }

    }

    @BindingAdapter("textViewText")
    public static void setTextView(final TextView view, final MutableLiveData<String> text) {
        try {
            ViewExtension viewExtension = new ViewExtension(view);
            LifecycleOwner lifecycleOwner = viewExtension.getLifecycleOwner();
            if (lifecycleOwner != null && text != null) {
                text.observe(lifecycleOwner, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        view.setSelected(true);
                        view.setText(s);
                    }
                });
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @BindingAdapter("setProfileImage")
    public static void setProfileImage(final ImageView view, final MutableLiveData<String> url) {
        try {
            final ViewExtension viewExtension = new ViewExtension(view);
            LifecycleOwner lifecycleOwner = viewExtension.getLifecycleOwner();
            if (lifecycleOwner != null && url != null) {
                url.observe(lifecycleOwner, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        loadImage(view.getContext(), view, s, R.drawable.profile_pic_default);

                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void loadImage(Context context, ImageView view, String url, int profile_pic_default) {
        try {
            if (url.trim().isEmpty()) {
                view.setImageResource(profile_pic_default);
            } else {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(profile_pic_default);
                requestOptions.error(profile_pic_default);
                Glide.with(context)
                        .load(url)
                        .thumbnail(0.1f)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .apply(requestOptions)
                        .into(view);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @BindingAdapter("setVerticalAdapter")
    public static void bindRecyclerAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }


}
