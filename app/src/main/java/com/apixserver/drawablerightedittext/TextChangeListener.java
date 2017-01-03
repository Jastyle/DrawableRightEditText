package com.apixserver.drawablerightedittext;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;

/**
 * Created by jast on 2016/12/27.
 */

public interface TextChangeListener {
    void afterTextChanged(Editable editable);
    void onTextChanged(CharSequence s, int start, int before, int count);
    void onClickDrawRight(EditText editText);
    void onFocusChange(View view, boolean b);
}
