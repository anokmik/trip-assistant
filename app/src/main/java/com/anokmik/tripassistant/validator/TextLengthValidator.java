package com.anokmik.tripassistant.validator;

public class TextLengthValidator {

    public boolean higherThan(String text, int length) {
        return notNull(text) && (text.length() > length);
    }

    public boolean lowerThan(String text, int length) {
        return notNull(text) && (text.length() < length);
    }

    private boolean notNull(String text) {
        return text != null;
    }

}