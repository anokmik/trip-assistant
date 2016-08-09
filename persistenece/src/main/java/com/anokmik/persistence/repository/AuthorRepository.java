package com.anokmik.persistence.repository;

import com.anokmik.persistence.model.Author;
import com.anokmik.persistence.model.Author_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

public class AuthorRepository extends Repository<Author> {

    @Override
    protected Class<Author> getType() {
        return Author.class;
    }

    public void add(String firstName, String lastName, boolean isActive) {
        SQLite.insert(getType())
                .columns(Author_Table.firstName, Author_Table.lastName, Author_Table.isActive)
                .values(firstName, lastName, isActive)
                .execute();
    }

    public Author get(long id) {
        return get(Author_Table.id.eq(id));
    }

    public Author get(String firstName, String lastName) {
        return get(Author_Table.firstName.eq(firstName), Author_Table.lastName.eq(lastName));
    }

    public Author getActive() {
        return get(Author_Table.isActive.is(true));
    }

    public void setAllActive(boolean isActive) {
        setAll(Author_Table.isActive.is(isActive));
    }

}