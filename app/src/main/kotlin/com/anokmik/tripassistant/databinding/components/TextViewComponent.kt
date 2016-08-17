package com.anokmik.tripassistant.databinding.components

import android.databinding.BindingAdapter
import android.text.Spanned
import android.widget.EditText
import android.widget.TextView

class TextViewComponent {

    @BindingAdapter("android:text")
    fun setText(view: TextView, text: CharSequence?) {
        val oldText = view.text
        if (text !== oldText && (text != null || oldText.length != 0)) {
            if (text is Spanned) {
                if (text == oldText) {
                    return
                }
            } else if (!haveContentsChanged(text, oldText)) {
                return
            }

            if (view is EditText) view.append(text) else view.setText(text)
        }
    }

    private fun haveContentsChanged(str1: CharSequence?, str2: CharSequence?): Boolean {
        if (str1 == null != (str2 == null)) {
            return true
        } else if (str1 == null) {
            return false
        } else if (str2 == null) {
            return true
        } else {
            val length = str1.length
            if (length != str2.length) {
                return true
            } else {
                for (i in 0..length - 1) {
                    if (str1[i] != str2[i]) {
                        return true
                    }
                }
                return false
            }
        }
    }

}