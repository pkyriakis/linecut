package com.panaceasoft.awesomematerial.utils.card;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

/**
 * Created by Panacea-Soft on 7/18/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class CreditCardNumberFormattingTextWatcher implements TextWatcher {

    private boolean lock;
    private CardActionListener cardActionListener;

    public CreditCardNumberFormattingTextWatcher(CardActionListener cardActionListener) {
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
        if (lock || s.length() > 19) {
            return;
        }
        lock = true;
        for (int i = 4; i < s.length(); i += 5) {
            if (s.toString().charAt(i) != ' ') {
                s.insert(i, " ");
            }
        }
        cardActionListener.cardNumberChanged(s.toString());
        lock = false;
    }
}
