package com.apixserver.drawablerightedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private DrawableRightEditText rightEditText_cancel, rightEditText_pswd;
    private static boolean pswdstatus = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
    }
    private void findView() {
        rightEditText_cancel = (DrawableRightEditText) findViewById(R.id.edit_cancel);
        rightEditText_pswd = (DrawableRightEditText) findViewById(R.id.edit_pswd);
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
    }

}
