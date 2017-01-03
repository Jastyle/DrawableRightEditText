package com.apixserver.drawablerightedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by jast on 2016/8/30.
 */
public class DrawableRightEditText extends EditText implements TextWatcher,View.OnFocusChangeListener{

    private TextChangeListener textChangeListener;
    private Drawable drawable;
    private boolean isshowRightDrawable;
    public DrawableRightEditText(Context context) {
        super(context);
        init(true);
    }

    public DrawableRightEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DrawableRightEditText);
        isshowRightDrawable = typedArray.getBoolean(R.styleable.DrawableRightEditText_showRightDrawable,false);
        typedArray.recycle();
        init(isshowRightDrawable);
    }

    public DrawableRightEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DrawableRightEditText);
        isshowRightDrawable = typedArray.getBoolean(R.styleable.DrawableRightEditText_showRightDrawable,false);
        typedArray.recycle();
        init(isshowRightDrawable);
    }
    //初始化view
    private void init(boolean showRightDrawable) {
        drawable = this.getCompoundDrawables()[2];
        this.addTextChangedListener(this);
        setRightDrawableVisible(showRightDrawable);
        this.setLongClickable(false);
        this.setOnFocusChangeListener(this);
    }

    public void setTextChangeListener(TextChangeListener textChangeListener) {
        this.textChangeListener = textChangeListener;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (textChangeListener != null) {
            textChangeListener.onFocusChange(view, b);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if ((event.getX() > (getWidth() - getTotalPaddingRight()))
                        && (event.getX() < (getWidth() - getPaddingRight()))) {
                    textChangeListener.onClickDrawRight(this);
                }
                break;
            default:

        }
        return super.onTouchEvent(event);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        textChangeListener.afterTextChanged(editable);
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (textChangeListener != null) {
            textChangeListener.onTextChanged(text,start,lengthBefore,lengthAfter);
        }
    }

    public void setRightDrawableVisible(boolean isvisible) {
        final Drawable rigthdrawable;
        if (isvisible) {
            rigthdrawable = drawable;
        }else {
            rigthdrawable = null;
        }
        if (rigthdrawable != null) {
            rigthdrawable.setBounds(0, 0, rigthdrawable.getMinimumWidth(), rigthdrawable.getMinimumHeight());
        }
        setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1],rigthdrawable,getCompoundDrawables()[3]);
    }

}
