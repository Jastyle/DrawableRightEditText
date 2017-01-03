package com.apixserver.drawablerightedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private DrawableRightEditText rightEditText_cancel, rightEditText_pswd,editable_et;
    private static boolean editstatus = true;//是否可编辑
    private static boolean pswdstatus = false;//是否显示密码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
    }
    private void findView() {
        rightEditText_cancel = (DrawableRightEditText) findViewById(R.id.edit_cancel);
        rightEditText_pswd = (DrawableRightEditText) findViewById(R.id.edit_pswd);
        editable_et = (DrawableRightEditText) findViewById(R.id.editable_et);
        rightEditText_cancel.setTextChangeListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length()>0) {
                    rightEditText_cancel.setRightDrawableVisible(true);
                }else {
                    rightEditText_cancel.setRightDrawableVisible(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onClickDrawRight(EditText editText) {
                rightEditText_cancel.setText("");
            }

            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });
        rightEditText_pswd.setTextChangeListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onClickDrawRight(EditText editText) {
                if (pswdstatus) {//可见时
                    rightEditText_pswd.setDrawable(getResources().getDrawable(R.mipmap.icon_showpswd));
                    rightEditText_pswd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
                    rightEditText_pswd.setSelection(rightEditText_pswd.getText().toString().trim().length());
                    pswdstatus = false;
                }else {
                    rightEditText_pswd.setDrawable(getResources().getDrawable(R.mipmap.icon_nopswd));
                    rightEditText_pswd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    rightEditText_pswd.setSelection(rightEditText_pswd.getText().toString().trim().length());
                    pswdstatus = true;
                }
            }

            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });
        editable_et.setTextChangeListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onClickDrawRight(EditText editText) {
                if (editstatus) {//可编辑
                    editable_et.setFocusableInTouchMode(true);
                    editable_et.setSelection(editable_et.getText().toString().trim().length());
                    editable_et.setDrawable(getResources().getDrawable(R.mipmap.icon_cancel));
                    editstatus = false;
                }else {
                    editable_et.setText("");
                }
            }

            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    editable_et.setFocusableInTouchMode(false);
                    editable_et.setDrawable(getResources().getDrawable(R.mipmap.icon_edit));
                    editable_et.setRightDrawableVisible(true);
                    editstatus = true;
                }else {
                    editable_et.setFocusableInTouchMode(true);
                    editable_et.setDrawable(getResources().getDrawable(R.mipmap.icon_cancel));
                    editable_et.setRightDrawableVisible(true);
                    editstatus = false;
                }
            }
        });
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            isShouldHideInput(v,ev);
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                //清除焦点
                v.clearFocus();
                return true;
            }
        }
        return false;
    }
}
