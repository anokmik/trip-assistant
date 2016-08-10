package com.anokmik.tripassistant.databinding.adapter

interface OnItemLongClickListener<in T> {

    fun onItemLongClick(item: T?, position: Int)

}