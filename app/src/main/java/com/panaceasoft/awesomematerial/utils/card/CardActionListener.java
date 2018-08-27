package com.panaceasoft.awesomematerial.utils.card;

/**
 * Created by Panacea-Soft on 7/18/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public interface CardActionListener {

    void cardNumberChanged(String cardNumber);
    void cardNameChanged(String name);
    void cardExpiryDateChanged(String date);
    void cvvChanged(String cvv);
}
