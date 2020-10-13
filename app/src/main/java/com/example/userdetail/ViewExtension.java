package com.example.userdetail;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;

public class ViewExtension {

    LifecycleOwner lifecycleOwner;
    Context viewContext;

    public ViewExtension(View view) {
        viewContext = view.getContext();
        lifecycleOwner = getOwner(view);
    }

    private LifecycleOwner getOwner(View view) {
        Context context = view.getContext();
        while (!(context instanceof LifecycleOwner)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return (LifecycleOwner) context;
    }

    public LifecycleOwner getLifecycleOwner() {
        return lifecycleOwner;
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    public Context getViewContext() {
        return viewContext;
    }

    public void setViewContext(Context viewContext) {
        this.viewContext = viewContext;
    }
}
