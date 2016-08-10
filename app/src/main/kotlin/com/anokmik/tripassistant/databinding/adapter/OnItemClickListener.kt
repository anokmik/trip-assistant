package com.anokmik.tripassistant.databinding.adapter

interface OnItemClickListener<in T> {

    fun onItemClick(item: T?, position: Int)

}