package com.anokmik.persistence.repository;

import com.anokmik.persistence.model.User;
import com.anokmik.persistence.model.User_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

public class UserRepository extends Repository<User> {

    @Override
    protected Class<User> getType() {
        return User.class;
    }

    public void add(String firstName, String lastName, boolean isActive) {
        SQLite.insert(getType())
                .columns(User_Table.firstName, User_Table.lastName, User_Table.isActive)
                .values(firstName, lastName, isActive)
                .execute();
    }

    public User get(long id) {
        return get(User_Table.id.eq(id));
    }

    public User get(String firstName, String lastName) {
        return get(User_Table.firstName.eq(firstName), User_Table.lastName.eq(lastName));
    }

    public User getActive() {
        return get(User_Table.isActive.is(true));
    }

    public void setAllActive(boolean isActive) {
        setAll(User_Table.isActive.is(isActive));
    }

}