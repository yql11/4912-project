package com.example.smart_agriculture_sys;

import android.widget.EditText;

/*
    Interface for the methods isEmpty and validEmail
 */

public interface checkTextFields {
    boolean isEmpty(EditText editText);

    boolean validEmail(EditText editText);

    public boolean isMatch(EditText editTextOne, EditText editTextTwo);
}