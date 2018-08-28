package com.mylinecut.linecut.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

//common UI methods and listeners
public class CommonUI {

    public CommonUI(){

    }

    // Removes the error when the text in the given edittext is changed
    public void removeErrorsOnTextChange(final EditText itemText)
    {

        itemText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                itemText.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
