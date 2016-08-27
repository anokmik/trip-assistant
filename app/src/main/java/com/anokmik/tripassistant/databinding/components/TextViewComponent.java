package com.anokmik.tripassistant.databinding.components;

import android.databinding.BindingAdapter;
import android.text.Spanned;
import android.widget.TextView;

public final class TextViewComponent {

    @BindingAdapter("android:text")
    public void setText(TextView view, CharSequence text) {
        CharSequence oldText = view.getText();
        if (text != oldText && (text != null || oldText.length() != 0)) {
            if (text instanceof Spanned) {
                if (text.equals(oldText)) {
                    return;
                }
            } else if (!haveContentsChanged(text, oldText)) {
                return;
            }

            view.setText(text);
        }
    }

    private boolean haveContentsChanged(CharSequence str1, CharSequence str2) {
        if (str1 == null != (str2 == null)) {
            return true;
        } else if (str1 == null) {
            return false;
        } else {
            int length = str1.length();
            if (length != str2.length()) {
                return true;
            } else {
                for (int i = 0; i < length; ++i) {
                    if (str1.charAt(i) != str2.charAt(i)) {
                        return true;
                    }
                }

                return false;
            }
        }
    }

}