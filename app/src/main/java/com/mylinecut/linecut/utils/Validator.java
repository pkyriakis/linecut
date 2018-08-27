package com.mylinecut.linecut.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Validator {

    // validaes the given date string according to the given date format
    public boolean isThisDateValid(String dateToValidate, String dateFromat){

        if(dateToValidate == null){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);

        try {
            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean isValidEmail(String target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }


}
