package com.anokmik.persistence.repository

import com.anokmik.persistence.model.PhotoAttachment

open class PhotoAttachmentRepository : Repository<PhotoAttachment>(PhotoAttachment::class.java)