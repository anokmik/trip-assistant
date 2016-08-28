package com.anokmik.tripassistant.util.contract.view

interface ViewListItem {

    val rowItemLayoutId: Int

    val itemBindingId: Int

    val adapterPositionProviderBindingId: Int

    val itemListenerBindingId: Int

    val itemIsEditingBindingId: Int

}