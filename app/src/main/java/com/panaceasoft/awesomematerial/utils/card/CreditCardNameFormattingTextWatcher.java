package com.panaceasoft.awesomematerial.utils.card;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

/**
 * Created by Panacea-Soft on 7/18/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class CreditCardNameFormattingTextWatcher implements TextWatcher {

    private boolean lock;
    private CardActionListener cardActionListener;

    public CreditCardNameFormattingTextWatcher(CardActionListener cardActionListener) {
        this.cardActionListener = cardActionListener;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d("TEAMPS", "onTextChanged");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.d("TEAMPS", "beforeTextChanged");
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.d("TEAMPS", lock + " afterTextChanged " + s.toString());

        lock = true;

        cardActionListener.cardNameChanged(s.toString());
        lock = false;
    }
}

