package com.anokmik.tripassistant.model;

import android.databinding.BaseObservable;

import com.anokmik.persistence.model.User;

public final class ObservableUser extends BaseObservable {

    private User user;

    public ObservableUser(User user) {
        this.user = user;
    }

    public User get() {
        return user;
    }

    public void set(User user) {
        this.user = user;
        notifyChange();
    }

    public String getFirstName() {
        return user.firstName;
    }

    public void setFirstName(String firstName) {
        user.firstName = firstName;
    }

    public String getLastName() {
        return user.lastName;
    }

    public void setLastName(String lastName) {
        user.lastName = lastName;
    }

    public boolean isActive() {
        return user.isActive;
    }

    public void setActive(boolean isActive) {
        user.isActive = isActive;
    }

    public void save() {
        user.save();
    }

    public void delete() {
        user.delete();
    }

}