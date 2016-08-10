package com.anokmik.tripassistant.validator

open class TextLengthValidator {

    fun higherThan(text: String?, length: Int): Boolean {
        return length(text) > length
    }

    fun lowerThan(text: String, length: Int): Boolean {
        return length(text) < length
    }

    private fun length(text: String?): Int {
        return text?.length ?: 0
    }

}