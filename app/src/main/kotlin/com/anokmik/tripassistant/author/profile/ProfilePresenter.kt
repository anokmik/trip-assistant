package com.anokmik.tripassistant.author.profile

import android.databinding.ObservableBoolean
import com.anokmik.persistence.repository.AuthorRepository
import com.anokmik.tripassistant.validator.AuthorTextLengthValidator

class ProfilePresenter(private val view: ProfileContract.View, val authorId: Long) : ProfileContract.Presenter {

    val firstNameValid = ObservableBoolean(true)
    val lastNameValid = ObservableBoolean(true)
    val isEditing = ObservableBoolean(false)

    private val authorRepository = AuthorRepository()
    private val validator = AuthorTextLengthValidator(firstNameValid, lastNameValid)
    private val author = authorRepository.get(authorId)

    var authorFirstName: String?
        get() = author?.firstName
        set(firstName) {
            author?.firstName = firstName
        }

    var authorLastName: String?
        get() = author?.lastName
        set(lastName) {
            author?.lastName = lastName
        }

    init {
        view.setProfileTitle()
    }

    override fun save() {
        if (validator.validFields(author?.firstName, author?.lastName)) {
            author?.save()
            view.back()
        }
    }

    override fun cancel() {
        view.setProfileTitle()
        isEditing.set(false)
    }

    override fun edit() {
        view.setEditAuthorTitle()
        isEditing.set(true)
    }

    override fun delete() {
        author?.delete()
        view.back()
    }

    override fun signOut() {
        author?.isActive = false
        author?.save()
        view.back()
    }

}