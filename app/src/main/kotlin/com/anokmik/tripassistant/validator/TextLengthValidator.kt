package com.anokmik.tripassistant.validator

open class TextLengthValidator {

    fun higherThan(text: String?, length: Int) = length(text) > length

    fun lowerThan(text: String, length: Int) = length(text) < length

    private fun length(text: String?) = text?.length ?: 0

}