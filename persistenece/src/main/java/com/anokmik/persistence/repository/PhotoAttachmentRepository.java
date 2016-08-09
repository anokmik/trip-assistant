package com.anokmik.persistence.repository;

import com.anokmik.persistence.model.PhotoAttachment;

public class PhotoAttachmentRepository extends Repository<PhotoAttachment> {

    @Override
    protected Class<PhotoAttachment> getType() {
        return PhotoAttachment.class;
    }

}