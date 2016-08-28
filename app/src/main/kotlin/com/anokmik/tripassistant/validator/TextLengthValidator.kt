package com.anokmik.tripassistant.validator

import android.text.TextUtils

class TextLengthValidator {

    fun higherThan(text: String?, @Threshold threshold: Long) = notEmpty(text) && ((text?.length ?: 0) > threshold)

    fun lowerThan(text: String?, @Threshold threshold: Long) = notEmpty(text) && ((text?.length ?: 0) < threshold)

    fun notEmpty(text: String?) = !TextUtils.isEmpty(text)

}