package com.sakurafish.expandablerecyclerview.sample;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.view.View;

public class RecyclerItemViewModel extends BaseObservable {

    private String expandButtonText;
    private String text1;
    private String text2;

    private View.OnClickListener onClickListener;
    private boolean expanded = false;

    RecyclerItemViewModel(@NonNull String text1, @NonNull String text2) {
        this.text1 = text1;
        this.text2 = text2;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    @Bindable
    public String getExpandButtonText() {
        return expandButtonText;
    }

    public void setExpandButtonText(@NonNull String expandButtonText) {
        this.expandButtonText = expandButtonText;
    }

    public void onClickExpandButton(View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public void setOnClickListener(@NonNull View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return this.expanded;
    }
}
